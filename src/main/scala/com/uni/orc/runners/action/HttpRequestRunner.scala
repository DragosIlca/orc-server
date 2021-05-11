package com.uni.orc.runners.action

import com.uni.orc.models.parsed.Action.HttpRequest

import scala.concurrent.Future

class HttpRequestRunner extends ActionRunner[HttpRequest] {
	override def run(action: HttpRequest): Future[Either[String, Unit]] = {
		Future.successful(Right(println(s"running http request ${action.instruction}")))
	}
}

object HttpRequestRunner {
	def apply(): HttpRequestRunner = new HttpRequestRunner()
}