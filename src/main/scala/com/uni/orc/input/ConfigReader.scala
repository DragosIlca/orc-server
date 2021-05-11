package com.uni.orc.input

import com.uni.orc.models.raw.{PluginsConfig, RawPlugin}
import play.api.libs.json.Json

import scala.io.Source
import com.uni.orc.json.JsonImplicits._

object ConfigReader {
	lazy val jsonString : String = Source.fromResource("config.json").mkString

	def readConfig(): PluginsConfig = Json.parse(jsonString).as[PluginsConfig]
}
