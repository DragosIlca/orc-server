package com.uni.orc.converters

import com.uni.orc.models.Config.{Config, DockerCommandConfig, HttpRequestConfig}
import com.uni.orc.models.parsed.Action.{Action, CLICommand, ConfiguredAction, DockerCommand, HttpRequest}
import com.uni.orc.models.raw.RawAction
import com.uni.orc.types.Action

object ModelConverter {
	val emptyConfigError = "Config must not be null"
	val wrongConfigError = "Wrong config for action"

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
