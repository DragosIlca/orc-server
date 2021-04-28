package com.uni.orc.converters

import com.uni.orc.models.Config.{DockerCommandConfig, HttpRequestConfig}
import com.uni.orc.models.parsed.Action.{Action, CLICommand, DockerCommand, HttpRequest}
import com.uni.orc.models.raw.RawAction
import com.uni.orc.types.Action

object ModelConverter {
	def convertAction(rawAction: RawAction): Action[_] = {
		rawAction.actionType match {
			case Action.CLI_COMMAND => CLICommand(rawAction.instruction)
			case Action.DOCKER_COMMAND => DockerCommand(rawAction.instruction, Some(rawAction.config.asInstanceOf[DockerCommandConfig]))
			case Action.HTTP_REQUEST => HttpRequest(rawAction.instruction, Some(rawAction.config.asInstanceOf[HttpRequestConfig]))
		}
	}
}
