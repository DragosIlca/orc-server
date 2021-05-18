package com.uni.orc.orchestrator

import org.scalatest.funsuite.AsyncFunSuite

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

class OrchestratorTest extends AsyncFunSuite {

	implicit val ec: ExecutionContext = ExecutionContext.global

	test("Test Config") {
		Orchestrator().run() flatMap {
			case Left(e) =>
				println(e)
				fail
			case Right(_) => succeed
		}
	}
}
