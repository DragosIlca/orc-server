package com.uni.orc.runner.action

import com.uni.orc.models.parsed.Action.HttpRequest

class HttpRequestRunner extends ActionRunner[HttpRequest] {
	override def run(action: HttpRequest): Either[String, Unit] = {
		Right(println(s"running http request ${action.instruction}"))
	}
}

object HttpRequestRunner {
	def apply(): HttpRequestRunner = new HttpRequestRunner()
}