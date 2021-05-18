package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action.CLICommand

import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process.{Process, ProcessLogger}

class CLICommandRunner extends ActionRunner[CLICommand] {
	import ActionRunner._
	import CLICommandRunner._

	override def run(action: CLICommand)(implicit ec: ExecutionContext, processLogger: ProcessLogger): Future[Either[String, Unit]] = {
		val process = Process(windowsPrefix + action.instruction)

		Future.successful(process.run(processLogger).exitValue()) map {
			case 0 => Right(())
			case _ => Left(consoleErrorMessage)
		}
	}
}

object CLICommandRunner {
	val windowsPrefix = "cmd /C "

	def apply(): CLICommandRunner = new CLICommandRunner()
}