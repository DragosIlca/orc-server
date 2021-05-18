package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action._

import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process.ProcessLogger

trait ActionRunner[T <: Action] {
  def run(action: T)(implicit ec: ExecutionContext, processLogger: ProcessLogger): Future[Either[String, Unit]]
}

object ActionRunner {
  val consoleErrorMessage = "An error has occurred when trying to execute a command, check debug.log for details"

  def run(action: Action)(implicit ec: ExecutionContext, processLogger: ProcessLogger): Future[Either[String, Unit]] = action match {
    case _: CLICommand => CLICommandRunner().run(action.asInstanceOf[CLICommand])
    case _: DockerCommand => DockerCommandRunner().run(action.asInstanceOf[DockerCommand])
    case _: HttpRequest => HttpRequestRunner().run(action.asInstanceOf[HttpRequest])
  }
}

