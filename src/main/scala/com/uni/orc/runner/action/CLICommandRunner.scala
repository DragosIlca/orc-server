package com.uni.orc.runner.action

import com.uni.orc.models.parsed.Action.CLICommand

class CLICommandRunner extends ActionRunner[CLICommand] {
	override def run(action: CLICommand): Either[String, Unit] = {
		Right(println(s"running cli command: ${action.instruction}"))
	}
}

object CLICommandRunner {
	def apply(): CLICommandRunner = new CLICommandRunner()
}