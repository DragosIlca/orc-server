package com.uni.orc.runners.action

import cats.effect.Sync
import com.uni.orc.models.Action.CLICommand

import scala.sys.process.{Process, ProcessLogger}

class CLICommandRunner extends ActionRunner[CLICommand] {

	import ActionRunner._
	import CLICommandRunner._

	override def run[F[_] : Sync](action: CLICommand)(implicit processLogger: ProcessLogger): F[Either[String, Unit]] = {
		val process = Process(windowsPrefix + action.instruction)

		Sync[F].delay(
			process.run(processLogger).exitValue() match {
				case 0 => Right(())
				case _ => Left(consoleErrorMessage)
			}
			)
	}
}

object CLICommandRunner {
	val windowsPrefix = "cmd /C "

	def apply(): CLICommandRunner = new CLICommandRunner()
}