package com.uni.orc.runners.action

import cats.effect.IO
import com.uni.orc.models.parsed.Action.HttpRequest

import scala.sys.process.ProcessLogger

class HttpRequestRunner extends ActionRunner[HttpRequest] {
	override def run(action: HttpRequest)(implicit processLogger: ProcessLogger): IO[Either[String, Unit]] = {
		IO(Right(println(s"running http request ${action.instruction}")))
	}
}

object HttpRequestRunner {
	def apply(): HttpRequestRunner = new HttpRequestRunner()
}