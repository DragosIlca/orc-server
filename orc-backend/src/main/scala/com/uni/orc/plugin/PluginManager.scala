package com.uni.orc.plugin

import java.io.{File, FileWriter}
import play.api.libs.json.Json
import com.uni.orc.json.JsonImplicits._
import com.uni.orc.models.MarketPlugin

import scala.io.Source
import scala.reflect.io.Directory
import scala.sys.process.{Process, ProcessLogger}

object PluginManager {
	val userFolderPath: String = System.getProperty("user.home")
	val projectsPath: String =  s"$userFolderPath/.orc/projects"
	val pluginsPath: String = s"$userFolderPath/.orc/plugins"
	val pluginAlreadyExists: String = "Plugin already exists"
	val pluginMissing: String = "Plugin missing"
	val projectMissing: String = "Project missing"
	val pluginInfoName: String = "plugin-info.json"
	val cloneError: String = "Error cloning git repository"
	def instructionError(instruction: String): String = s"Error executing instruction $instruction"

	def createProject(projectName: String, url: String, repoName: String)(implicit processLogger: ProcessLogger): Either[String, Unit] =
		Process(s"cmd /C git clone $url ${projectName.projectPath}/$repoName").run(processLogger).exitValue() match {
			case 0 => Right(())
			case _ => Left(cloneError)
		}

	def removeProject(projectName: String): Either[String, Unit] = {
		val projectPath = projectName.projectPath

		val file = new File(projectPath)
		val directory = new Directory(file)

		if (file.exists()) {
			Right(directory.deleteRecursively())
		}
		else {
			Left(projectMissing)
		}
	}

	def addPlugin(name: String, pluginContent: MarketPlugin): Either[String, Unit] = {
		val filePath = name.pluginFilePath
		createPluginsFolderIfNotExists(name)
		val file = new File(filePath)
		if (file.createNewFile()) {
			Right(writeToFile(filePath, Json.toJson(pluginContent).toString))
		}
		else {
			Left(pluginAlreadyExists)
		}
	}

	def removePlugin(name: String): Either[String, Unit] = {
		val pluginPath = name.pluginPath

		val file = new File(pluginPath)
		val directory = new Directory(file)

		if (file.exists()) {
			Right(directory.deleteRecursively())
		}
		else {
			Left(pluginMissing)
		}
	}

	def updatePlugin(name: String, pluginContent: MarketPlugin): Either[String, Unit] = {
		val filePath = name.pluginFilePath

		if (new File(filePath).exists()) {
			Right(writeToFile(filePath, Json.toJson(pluginContent).toString))
		}
		else {
			Left(pluginMissing)
		}
	}

	def installPlugin(projectName: String, pluginName: String)(implicit processLogger: ProcessLogger): Either[String, Unit] = {
		val pluginPath = pluginName.pluginFilePath
		val projectPath = projectName.projectPath
		val projectPluginsPath = s"$projectPath/plugins/$pluginName/$pluginInfoName"

		val project = new File(projectPath)
		val plugin = new File(pluginPath)

		(project.exists(), plugin.exists()) match {
			case (false, _) => Left(projectMissing)
			case (_, false) => Left(pluginMissing)
			case _ =>
				val source = Source.fromFile(pluginPath)
				val plugin = Json.parse(source.mkString).as[MarketPlugin]

				createDirectories(projectName, pluginName)
				writeToFile(projectPluginsPath, Json.toJson(plugin).toString)
				runInstallationTasks(plugin.installation)
		}
	}

	def uninstallPlugin(projectName: String, pluginName: String): Either[String, Unit] = {
		val projectPath = projectName.projectPath
		val projectPluginPath = s"$projectPath/plugins/$pluginName"

		val project = new File(projectPath)
		val projectPlugin = new Directory(new File(projectPluginPath))

		(project.exists(), projectPlugin.exists) match {
			case (false, _) => Left(projectMissing)
			case (_, false) => Left(pluginMissing)
			case _ =>
				Right(projectPlugin.deleteRecursively())
		}
	}

	private def runInstallationTasks(instructions: List[String])(implicit processLogger: ProcessLogger): Either[String, Unit] = {
		instructions
			.map(instruction => (instruction, Process(s"cmd /C $instruction")
			.run(processLogger).exitValue()) match {
				case (_, 0) => Right(())
				case (instr, _) => Left(instructionError(instr))
			})
			.partitionMap(identity)
			._1
			.headOption
			.toLeft()
	}

	private def createDirectories(projectName: String, pluginName: String): Unit = {
		val projectPluginsPath = projectName.projectPluginsPath
		val projectPluginResourcesPath = s"$projectPluginsPath/$pluginName/resources/"

		new File(projectPluginResourcesPath).mkdir()
		new File(projectPluginsPath).mkdir()
		new File(s"$projectPluginsPath/$pluginName").mkdir()
	}

	private def writeToFile(filePath: String, content: String): Unit = {
		val writer = new FileWriter(filePath)
		writer.write(content)
		writer.close()
	}

	private def createPluginsFolderIfNotExists(name: String): Unit = {
		val specificPluginPath = name.pluginPath

		if(!new File(pluginsPath).exists()) {
			new File(pluginsPath).mkdir()
		}

		if (!new File(specificPluginPath).exists()) {
			new File(specificPluginPath).mkdir()
		}
	}

	implicit class ProjectPaths(name: String) {
		def projectPath = s"$projectsPath/$name"
		def projectPluginsPath = s"$projectPath/plugins"
		def pluginPath = s"$pluginsPath/$name"
		def pluginResourcesPath = s"$projectPluginsPath/$name/resources"
		def pluginFilePath = s"$pluginsPath/$name/$pluginInfoName"
	}
}
