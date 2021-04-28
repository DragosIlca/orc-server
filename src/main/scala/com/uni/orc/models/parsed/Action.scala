package com.uni.orc.models.parsed

import com.uni.orc.models.Config._

object Action {
	sealed trait Action[C <: Config] {
		def instruction: String
		def config: Option[C]
	}

	case class CLICommand(
		instruction: String,
		config     : Option[Config] = None
	) extends Action[Config]

	case class DockerCommand(
		instruction: String,
		config     : Option[DockerCommandConfig]
	) extends Action[DockerCommandConfig]

	case class HttpRequest(
		instruction: String,
		config     : Option[HttpRequestConfig]
	) extends Action[HttpRequestConfig]
}