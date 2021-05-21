package com.uni.orc.plugin

import org.apache.log4j.Logger
import org.scalatest.funsuite.AsyncFunSuite

import scala.sys.process.ProcessLogger

class PluginManagerTest extends AsyncFunSuite {
	val logger: Logger = Logger.getLogger(classOf[PluginManagerTest])

	implicit val processLogger: ProcessLogger = new ProcessLogger {
		override def out(s: => String): Unit = {
			logger.info(s)
		}
		override def err(s: => String): Unit = {
			logger.error(s)
		}
		override def buffer[T](f: => T): T = f
	}

	test("Test create project") {
		PluginManager.createProject("matrix-multiplier", "https://github.com/DragosIlca/matrix-multiplier.git", "matrix-multiplier") match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}

	test("Test remove project") {
		PluginManager.removeProject("matrix-multiplier") match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}

	test("Test add plugin") {
		val name = "idkPlugin"
		val content = """{
			              |  "plugins": [
			              |    {
			              |      "id": "inspector-git",
			              |      "name": "Inspector Git",
			              |      "description": "Description",
			              |      "version": "0.0.1",
			              |      "lifecycle": [
			              |        {
			              |          "hook": "run",
			              |          "action": {
			              |            "actionType": "cli",
			              |            "instruction": "java -jar ./tools/iglog.jar ../kafka"
			              |          }
			              |        }
			              |      ]
			              |    }
			              |  ]
			              |}""".stripMargin

		PluginManager.addPlugin(name, content) match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}

	test("Test remove plugin") {
		val name = "idkPlugin"

		PluginManager.removePlugin(name) match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}

	test("Test install plugin") {
		val projectName = "matrix-multiplier"
		val pluginName = "idkPlugin"

		PluginManager.installPlugin(projectName, pluginName) match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}

	test("Test uninstall plugin") {
		val projectName = "matrix-multiplier"
		val pluginName = "idkPlugin"

		PluginManager.uninstallPlugin(projectName, pluginName) match {
			case Left(err) =>
				fail(err)
			case Right(_) =>
				succeed
		}
	}
}
