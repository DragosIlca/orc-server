package com.uni.orc.orchestrator

import java.util.concurrent.Executors

import com.uni.orc.converters.ModelConverter
import com.uni.orc.input.ConfigReader
import cats.implicits._
import com.uni.orc.runners.plugin.PluginRunner

import scala.concurrent.ExecutionContext

class Orchestrator {
	import Orchestrator._

	def run(): Either[String, Unit] = {
		val config = ConfigReader.readConfig()

		for {
			parsedPlugins <- config.plugins.map(ModelConverter().convertPlugin).sequence
			result = parsedPlugins.map(PluginRunner.run)
		} yield result.sequence.map(_.sequence).map(_.map(_ => ()))
	}
}

object Orchestrator {
	implicit val executionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

	def apply(): Orchestrator = new Orchestrator()
}
