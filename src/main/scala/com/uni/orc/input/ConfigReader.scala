package com.uni.orc.input

import com.uni.orc.json.JsonImplicits._
import com.uni.orc.models.PluginsConfig
import play.api.libs.json.Json

import scala.io.Source

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
