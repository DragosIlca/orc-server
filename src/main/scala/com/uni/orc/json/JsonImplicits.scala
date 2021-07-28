package com.uni.orc.json

import ai.x.play.json.Jsonx
import com.uni.orc.models.Config._
import com.uni.orc.models.Action._
import com.uni.orc.models.{Command, ConfigurationField, MarketPlugin, PluginsConfig, Task}
import play.api.libs.json._

object JsonImplicits {
	implicit val httpRequestConfigFmt: Format[HttpRequestConfig] = Jsonx.formatCaseClass[HttpRequestConfig]
	implicit val dockerCommandConfigFmt: Format[DockerCommandConfig] = Jsonx.formatCaseClass[DockerCommandConfig]
	implicit val configFmt: Format[Config] = Jsonx.formatSealed[Config]

	implicit val dockerFmt: Format[DockerCommand] = Jsonx.formatCaseClass[DockerCommand]
	implicit val requestFmt: Format[HttpRequest] = Jsonx.formatCaseClass[HttpRequest]
	implicit val cliFmt: Format[CLICommand] = Jsonx.formatCaseClass[CLICommand]

	implicit val actionFmt: Format[Action] = Jsonx.formatSealed[Action]

	implicit val configFieldFmt: Format[ConfigurationField] = Jsonx.formatCaseClass[ConfigurationField]
	implicit val commandFmt: Format[Command] = Jsonx.formatCaseClass[Command]

	implicit val taskFmt: Format[Task] = Jsonx.formatCaseClass[Task]
	implicit val pluginFmt: Format[MarketPlugin] = Jsonx.formatCaseClass[MarketPlugin]

	implicit val pluginsConfigFmt: Format[PluginsConfig] = Jsonx.formatCaseClass[PluginsConfig]
}

