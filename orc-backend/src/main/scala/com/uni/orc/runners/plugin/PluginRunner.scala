package com.uni.orc.runners.plugin

import cats.effect.Sync
import cats.implicits._
import com.uni.orc.models.MarketPlugin
import com.uni.orc.runners.action.ActionRunner

import scala.sys.process.ProcessLogger

object PluginRunner {
	def run[F[_]: Sync](plugin: MarketPlugin)(implicit processLogger: ProcessLogger): F[Either[String, Unit]] =
		plugin.lifecycle
		      .map(_.action)
		      .map(ActionRunner.run[F])
		      .sequence
  	      .map(_.sequence)
		      .map(_.map(_ => ()))
}
