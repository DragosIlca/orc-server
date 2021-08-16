package com.uni.orc.runners.action

import cats.effect.IO
import com.uni.orc.models.Action.CLICommand

import scala.sys.process.{Process, ProcessLogger}

class CLICommandRunner extends ActionRunner[CLICommand] {
	import ActionRunner._
	import CLICommandRunner._

	override def run(action: CLICommand)(implicit processLogger: ProcessLogger): IO[Either[String, Unit]] = {
		val process = Process(windowsPrefix + action.instruction)

		IO(process.run(processLogger).exitValue()) map {
			case 0 => Right(())
			case _ => Left(consoleErrorMessage)
		}
	}
}

object CLICommandRunner {
	val windowsPrefix = "cmd /C "

	def apply(): CLICommandRunner = new CLICommandRunner()
}