package com.uni.orc.converters

import com.uni.orc.models.Config.{Config, DockerCommandConfig, HttpRequestConfig}
import com.uni.orc.models.parsed.Action.{Action, CLICommand, DockerCommand, HttpRequest}
import com.uni.orc.models.parsed.{Plugin, Task}
import com.uni.orc.models.raw.{RawAction, RawPlugin, RawTask}
import com.uni.orc.models.types.Action
import cats.implicits._

class ModelConverter {
	import ModelConverter._

	def convertPlugin(rawPlugin: RawPlugin): Either[String, Plugin] = {
		rawPlugin.lifecycle
		         .map(convertTask)
		         .sequence
		         .map(Plugin(rawPlugin.id, rawPlugin.name, rawPlugin.description, rawPlugin.version, _))
	}

	def convertTask(rawTask: RawTask): Either[String, Task] = {
		convertAction(rawTask.action).map(Task(rawTask.hook, _))
	}

	def convertAction(rawAction: RawAction): Either[String, Action] = {
		rawAction.actionType match {
			case Action.CLI_COMMAND => Right(CLICommand(rawAction.instruction))
			case Action.DOCKER_COMMAND =>
				rawAction.config.map(castConfig[DockerCommandConfig](_).map(DockerCommand(rawAction.instruction, _)))
			case Action.HTTP_REQUEST =>
				rawAction.config.map(castConfig[HttpRequestConfig](_).map(HttpRequest(rawAction.instruction, _)))
		}
	}

	private def castConfig[C <: Config](config: Config): Either[String, C] = {
		if (!config.isInstanceOf[C])
			Left(wrongConfigError)
		else
			Right(config.asInstanceOf[C])
	}

	implicit private def openOption(action: Option[Either[String, Action]]): Either[String, Action] = {
		action.getOrElse(Left(emptyConfigError))
	}
}

object ModelConverter {
	val emptyConfigError = "Config must not be null"
	val wrongConfigError = "Wrong config for action"

	def apply(): ModelConverter = new ModelConverter()
}