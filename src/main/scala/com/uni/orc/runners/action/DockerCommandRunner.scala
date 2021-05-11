package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action.DockerCommand

import scala.concurrent.Future

class DockerCommandRunner extends ActionRunner[DockerCommand] {
	override def run(action: DockerCommand): Future[Either[String, Unit]] = {
		Future.successful(Right(println(s"running docker command ${action.instruction}")))
	}
}

object DockerCommandRunner {
	def apply(): DockerCommandRunner = new DockerCommandRunner()
}