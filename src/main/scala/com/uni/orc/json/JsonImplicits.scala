package com.uni.orc.json

import ai.x.play.json.Jsonx
import com.uni.orc.models.Config._
import com.uni.orc.models.raw.{PluginsConfig, RawAction, RawPlugin, RawTask}
import play.api.libs.json._
import play.api.libs.functional.syntax._

object JsonImplicits {
	implicit val httpRequestConfigFmt: OFormat[HttpRequestConfig] = Jsonx.formatCaseClass[HttpRequestConfig]
	implicit val dockerCommandConfigFmt: OFormat[DockerCommandConfig] = Jsonx.formatCaseClass[DockerCommandConfig]
	implicit val configFmt: Format[Config] = Jsonx.formatSealed[Config]

	implicit val actionFmt: Reads[RawAction] = (
		(JsPath \ "actionType").read[String] and
			(JsPath \ "instruction").read[String] and
			(JsPath \ "config").readNullable[Config]
		) (RawAction.apply _)

	implicit val commandFmt: Reads[RawTask] = Json.reads[RawTask]
	implicit val pluginFmt: Reads[RawPlugin] = Json.reads[RawPlugin]
	implicit val pluginsConfigFmt: Reads[PluginsConfig] = Json.reads[PluginsConfig]
}

