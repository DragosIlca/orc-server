package com.uni.orc.orchestrator

import com.uni.orc.converters.ModelConverter
import com.uni.orc.input.ConfigReader
import cats.implicits._
import com.uni.orc.runner.plugin.PluginRunner

class Orchestrator {
	def run(): Either[String, Unit] = {
		val config = ConfigReader.readConfig()

		for {
			parsedPlugins <- config.plugins.map(ModelConverter().convertPlugin).sequence
			result = parsedPlugins.map(PluginRunner.run)
		} yield result
	}
}

object Orchestrator {
	def apply(): Orchestrator = new Orchestrator()
}
