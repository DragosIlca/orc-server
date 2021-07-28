package com.uni.orc.models

import com.uni.orc.models.Config.{Config, DockerCommandConfig, HttpRequestConfig}

object Action {
	sealed trait Action {
		def instruction: String
	}

	sealed trait Configurable[C <: Config] {
		def config: C
	}

	case class CLICommand(
		instruction     : String,
		final val config: Option[String] = None
	) extends Action

	case class DockerCommand(
		instruction: String,
		config     : DockerCommandConfig
	) extends Action with Configurable[DockerCommandConfig]

	case class HttpRequest(
		instruction: String,
		config     : HttpRequestConfig
	) extends Action with Configurable[HttpRequestConfig]
}
