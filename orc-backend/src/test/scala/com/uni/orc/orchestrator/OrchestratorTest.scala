package com.uni.orc.orchestrator

import cats.effect.IO
import org.scalatest.funsuite.AsyncFunSuite

import scala.language.postfixOps

class OrchestratorTest extends AsyncFunSuite {
	test("Test Config") {
		Orchestrator().run[IO]().unsafeToFuture() flatMap {
			case Left(e) =>
				println(e)
				fail
			case Right(_) => succeed
		}
	}
}
