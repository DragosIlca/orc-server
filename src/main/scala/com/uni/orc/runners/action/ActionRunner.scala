package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action._

import scala.concurrent.Future

trait ActionRunner[T <: Action] {
  def run(action: T): Future[Either[String, Unit]]
}

object ActionRunner {
  def run(action: Action): Future[Either[String, Unit]] = action match {
    case _: CLICommand => CLICommandRunner().run(action.asInstanceOf[CLICommand])
    case _: DockerCommand => DockerCommandRunner().run(action.asInstanceOf[DockerCommand])
    case _: HttpRequest => HttpRequestRunner().run(action.asInstanceOf[HttpRequest])
  }
}

