package com.uni.orc.json

import ai.x.play.json.Jsonx
import com.uni.orc.models.Config._
import com.uni.orc.models.parsed.Action.{Action, CLICommand, ConfiguredAction, DockerCommand, HttpRequest, UnconfiguredAction}
import com.uni.orc.models.parsed.{MarketPlugin, Task}
import com.uni.orc.models.raw.{PluginsConfig, RawAction, RawMarketPlugin, RawTask}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.uni.orc.models.common.{Command, ConfigurationField}

object JsonImplicits {
	implicit val httpRequestConfigFmt: OFormat[HttpRequestConfig] = Jsonx.formatCaseClass[HttpRequestConfig]
	implicit val dockerCommandConfigFmt: OFormat[DockerCommandConfig] = Jsonx.formatCaseClass[DockerCommandConfig]
	implicit val configFmt: Format[Config] = Jsonx.formatSealed[Config]

	implicit val dockerFmt: Format[DockerCommand] = Jsonx.formatCaseClass[DockerCommand]
	implicit val requestFmt: Format[HttpRequest] = Jsonx.formatCaseClass[HttpRequest]
	implicit val cliFmt: Format[CLICommand] = Jsonx.formatCaseClass[CLICommand]

	implicit val configuredActionFmt: Format[ConfiguredAction[_]] = Jsonx.formatSealed[ConfiguredAction[_]]
	implicit val dockerActionFmt: Format[ConfiguredAction[DockerCommandConfig]] = Jsonx.formatSealed[ConfiguredAction[DockerCommandConfig]]
	implicit val requestActionFmt: Format[ConfiguredAction[HttpRequestConfig]] = Jsonx.formatSealed[ConfiguredAction[HttpRequestConfig]]
	implicit val unconfiguredActionFmt: Format[UnconfiguredAction] = Jsonx.formatSealed[UnconfiguredAction]
	implicit val actionFmt: Format[Action] = Jsonx.formatSealed[Action]

	implicit val configFieldFmt: Format[ConfigurationField] = Jsonx.formatCaseClass[ConfigurationField]
	implicit val commandFmt: Format[Command] = Jsonx.formatCaseClass[Command]

//	implicit val rawActionFmt: Reads[RawAction] = (
//		(JsPath \ "actionType").read[String] and
//			(JsPath \ "instruction").read[String] and
//			(JsPath \ "config").readNullable[Config]
//		) (RawAction.apply _)
//
//	implicit val commandFmt: Reads[RawTask] = Json.reads[RawTask]
//	implicit val pluginFmt: Reads[RawMarketPlugin] = Json.reads[RawMarketPlugin]


	implicit val taskFmt: Reads[Task] = Json.reads[Task]
	implicit val pluginFmt: Reads[MarketPlugin] = Json.reads[MarketPlugin]

	implicit val pluginsConfigFmt: Reads[PluginsConfig] = Json.reads[PluginsConfig]
}

