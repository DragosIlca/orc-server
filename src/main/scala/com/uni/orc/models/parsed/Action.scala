package com.uni.orc.models.parsed

import com.uni.orc.models.Config._

object Action {
	sealed trait Action {
		def instruction: String
	}

	sealed trait ConfiguredAction[C <: Config] extends Action {
		def config: C
	}

	sealed trait UnconfiguredAction extends Action

	case class CLICommand(
		instruction: String
	) extends UnconfiguredAction

	case class DockerCommand(
		instruction: String,
		config     : DockerCommandConfig
	) extends ConfiguredAction[DockerCommandConfig]

	case class HttpRequest(
		instruction: String,
		config     : HttpRequestConfig
	) extends ConfiguredAction[HttpRequestConfig]
}