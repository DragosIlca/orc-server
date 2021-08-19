package com.uni.orc.runners.action

import cats.effect.Sync
import com.uni.orc.models.Action.DockerCommand

import scala.sys.process.ProcessLogger

class DockerCommandRunner extends ActionRunner[DockerCommand] {
	override def run[F[_]: Sync](action: DockerCommand)(implicit processLogger: ProcessLogger): F[Either[String, Unit]] = {
		Sync[F].delay(Right(println(s"running docker command ${action.instruction}")))
	}
}

object DockerCommandRunner {
	def apply(): DockerCommandRunner = new DockerCommandRunner()
}