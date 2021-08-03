package com.uni.orc.runners.action

import cats.effect.IO
import com.uni.orc.models.Action.DockerCommand
import com.uni.orc.models.Action.DockerCommand
import com.uni.orc.models.Action.DockerCommand

import scala.sys.process.ProcessLogger

class DockerCommandRunner extends ActionRunner[DockerCommand] {
	override def run(action: DockerCommand)(implicit processLogger: ProcessLogger): IO[Either[String, Unit]] = {
		IO(Right(println(s"running docker command ${action.instruction}")))
	}
}

object DockerCommandRunner {
	def apply(): DockerCommandRunner = new DockerCommandRunner()
}