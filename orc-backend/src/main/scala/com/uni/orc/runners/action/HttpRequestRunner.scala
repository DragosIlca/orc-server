package com.uni.orc.runners.action

import cats.effect.Sync
import com.uni.orc.models.Action.HttpRequest

import scala.sys.process.ProcessLogger

class HttpRequestRunner extends ActionRunner[HttpRequest] {
	override def run[F[_]: Sync](action: HttpRequest)(implicit processLogger: ProcessLogger): F[Either[String, Unit]] = {
		Sync[F].delay(Right(println(s"running http request ${action.instruction}")))
	}
}

object HttpRequestRunner {
	def apply(): HttpRequestRunner = new HttpRequestRunner()
}