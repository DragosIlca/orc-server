package com.uni.orc.runner

import com.uni.orc.models.parsed.Action.DockerCommand

class DockerCommandRunner extends ActionRunner[DockerCommand] {
	override def run(action: DockerCommand): Either[String, Unit] = {
		Right(println(s"running docker command ${action.instruction}"))
	}
}
