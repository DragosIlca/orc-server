package com.uni.orc.runners.action

import cats.effect.Sync
import com.uni.orc.models.Action.{Action, CLICommand, DockerCommand, HttpRequest}

import scala.sys.process.ProcessLogger

trait ActionRunner[T <: Action] {
  def run[F[_]: Sync](action: T)(implicit processLogger: ProcessLogger): F[Either[String, Unit]]
}

object ActionRunner {
  val consoleErrorMessage = "An error has occurred when trying to execute a command, check debug.log for details"

  def run[F[_]: Sync](action: Action)(implicit processLogger: ProcessLogger): F[Either[String, Unit]] = action match {
    case _: CLICommand => CLICommandRunner().run(action.asInstanceOf[CLICommand])
    case _: DockerCommand => DockerCommandRunner().run(action.asInstanceOf[DockerCommand])
    case _: HttpRequest => HttpRequestRunner().run(action.asInstanceOf[HttpRequest])
  }
}

