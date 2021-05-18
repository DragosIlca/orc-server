package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action.DockerCommand

import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process.ProcessLogger

class DockerCommandRunner extends ActionRunner[DockerCommand] {
	override def run(action: DockerCommand)(implicit ec: ExecutionContext, processLogger: ProcessLogger): Future[Either[String, Unit]] = {
		Future.successful(Right(println(s"running docker command ${action.instruction}")))
	}
}

object DockerCommandRunner {
	def apply(): DockerCommandRunner = new DockerCommandRunner()
}