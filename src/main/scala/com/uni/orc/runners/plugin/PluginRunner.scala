package com.uni.orc.runners.plugin

import com.uni.orc.models.parsed.Plugin
import com.uni.orc.runners.action.ActionRunner
import cats.implicits._

object PluginRunner {
	def run(plugin: Plugin): Either[String, Unit] =
		plugin.lifecycle
		      .map(_.action)
		      .map(ActionRunner.run)
		      .sequence
		      .map(_ => ())
}
