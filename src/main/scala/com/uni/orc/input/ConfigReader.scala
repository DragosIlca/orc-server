package com.uni.orc.input

import com.uni.orc.models.raw.{PluginsConfig, RawMarketPlugin}
import play.api.libs.json.Json

import scala.io.Source
import com.uni.orc.json.JsonImplicits._

object ConfigReader {
	val jsonFileName: String = "config.json"
	lazy val projectRoot: String = new java.io.File(".").getCanonicalPath
	lazy val jsonFile: Source = Source.fromFile(s"$projectRoot/$jsonFileName")

	def readConfig(): PluginsConfig = {
		val result = Json.parse(jsonFile.mkString).as[PluginsConfig]
		jsonFile.close()
		result
	}
}
