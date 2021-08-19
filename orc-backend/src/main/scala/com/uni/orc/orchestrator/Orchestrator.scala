package com.uni.orc.orchestrator

import cats.effect.Sync
import cats.implicits._
import com.uni.orc.input.ConfigReader
import com.uni.orc.runners.plugin.PluginRunner
import org.apache.log4j.Logger

import scala.sys.process.ProcessLogger

class Orchestrator {
	import Orchestrator._

	def run[F[_]: Sync](): F[Either[String, Unit]] = {
		val pluginsConfig = ConfigReader.readConfig()

		pluginsConfig.plugins.map(PluginRunner.run[F]).sequence.map(_.sequence.map(_ => ()))
	}
}

object Orchestrator {
	val logger = Logger.getLogger(Orchestrator.getClass)

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
