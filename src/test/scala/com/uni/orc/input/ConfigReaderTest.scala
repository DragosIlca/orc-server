package com.uni.orc.input

import com.uni.orc.models.raw.PluginsConfig
import org.scalatest.funsuite.AsyncFunSuite

class ConfigReaderTest extends AsyncFunSuite {

	test("Test Config") {
		val pluginConfig: PluginsConfig = ConfigReader.readConfig()

		assert(pluginConfig.plugins.size == 1)
	}
}
