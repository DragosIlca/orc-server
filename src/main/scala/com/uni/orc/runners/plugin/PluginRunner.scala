package com.uni.orc.runners.plugin

import com.uni.orc.models.parsed.Plugin
import com.uni.orc.runners.action.ActionRunner
import cats.implicits._

import scala.concurrent.{ExecutionContext, Future}

object PluginRunner {
	def run(plugin: Plugin)(implicit ec: ExecutionContext): Future[Either[String, Unit]] =
		plugin.lifecycle
		      .map(_.action)
		      .map(ActionRunner.run)
		      .sequence
  	      .map(_.sequence)
		      .map(_.map(_ => ()))
}
