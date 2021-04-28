package com.uni.orc.models.parsed

import com.uni.orc.models.Config._

object Action {
	sealed trait Action[C <: Config] {
		def instruction: String
		def config: C
	}

	case class CLICommand(
		instruction: String,
		config     : Nothing
	) extends Action[Nothing]

	case class DockerCommand(
		instruction: String,
		config     : DockerCommandConfig
	) extends Action[DockerCommandConfig]

	case class HttpRequest(
		instruction: String,
		config     : HttpRequestConfig
	) extends Action[HttpRequestConfig]
}