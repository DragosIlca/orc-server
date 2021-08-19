package com.uni.orc

import cats.effect.IO
import com.uni.orc.orchestrator.Orchestrator

object Main {
	def main(args: Array[String]): Unit = {
		Orchestrator().run[IO]().unsafeRunSync()
	}
}
