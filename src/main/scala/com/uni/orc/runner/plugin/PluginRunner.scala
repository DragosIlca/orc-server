package com.uni.orc.runner.plugin

import com.uni.orc.models.parsed.Plugin
import com.uni.orc.runner.action.ActionRunner
import cats.implicits._

object PluginRunner {
	def run(plugin: Plugin): Either[String, Unit] =
		plugin.lifecycle
		      .map(_.action)
		      .map(ActionRunner.run)
		      .sequence
		      .map(_ => ())
}
