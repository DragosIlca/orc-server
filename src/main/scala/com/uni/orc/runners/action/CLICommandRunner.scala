package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action.CLICommand

import scala.concurrent.Future
import scala.sys.process.stringToProcess

class CLICommandRunner extends ActionRunner[CLICommand] {
	override def run(action: CLICommand): Future[Either[String, Unit]] = {
		val process = stringToProcess("cmd /C " + action.instruction)

		Future.successful(Right(println(process.!!)))
	}
}

object CLICommandRunner {
	def apply(): CLICommandRunner = new CLICommandRunner()
}