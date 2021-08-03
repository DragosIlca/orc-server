package com.uni.orc.input

import com.uni.orc.models.PluginsConfig
import org.scalatest.funsuite.AsyncFunSuite

class ConfigReaderTest extends AsyncFunSuite {

	test("Test Config") {
		val pluginConfig: PluginsConfig = ConfigReader.readConfig()

		println(pluginConfig)

		assert(pluginConfig.plugins.size == 1)
	}
}
