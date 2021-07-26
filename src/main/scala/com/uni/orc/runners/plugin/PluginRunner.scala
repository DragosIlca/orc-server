package com.uni.orc.runners.plugin

import cats.effect.IO
import cats.implicits._
import com.uni.orc.models.parsed.MarketPlugin
import com.uni.orc.runners.action.ActionRunner

import scala.sys.process.ProcessLogger

object PluginRunner {
	def run(plugin: MarketPlugin)(implicit processLogger: ProcessLogger): IO[Either[String, Unit]] =
		plugin.lifecycle
		      .map(_.action)
		      .map(ActionRunner.run)
		      .sequence
  	      .map(_.sequence)
		      .map(_.map(_ => ()))
}
