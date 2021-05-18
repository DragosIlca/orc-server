package com.uni.orc.orchestrator

import java.util.concurrent.Executors

import cats.implicits._
import com.uni.orc.converters.ModelConverter
import com.uni.orc.input.ConfigReader
import com.uni.orc.runners.plugin.PluginRunner
import org.apache.log4j.Logger

import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process.ProcessLogger

class Orchestrator {
	import Orchestrator._

	def run(): Future[Either[String, Unit]] = {
		val config = ConfigReader.readConfig()

		(for {
			parsedPlugins <- config.plugins.map(ModelConverter().convertPlugin).sequence
			result = parsedPlugins.map(PluginRunner.run).sequence
		} yield result) match {
			case Left(e) =>
				Future.successful(Left(e))
			case Right(v) => v
				.map(_.sequence)
				.map(_.map(_ => ()))
		}
	}
}

object Orchestrator {
	val logger = Logger.getLogger(Orchestrator.getClass)

	implicit val executionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))
	implicit val processLogger: ProcessLogger = new ProcessLogger {
		override def out(s: => String): Unit = {
			logger.info(s)
		}
		override def err(s: => String): Unit = {
			logger.error(s)
		}
		override def buffer[T](f: => T): T = f
	}

	def apply(): Orchestrator = new Orchestrator()
}
