package com.uni.orc.models

import com.uni.orc.types.HttpRequest.HttpRequestType

object Config {
	sealed trait Config
	case class DockerCommandConfig(image: String, name: String) extends Config
	case class HttpRequestConfig(method: HttpRequestType) extends Config
}
