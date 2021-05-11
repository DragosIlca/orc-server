package com.uni.orc.orchestrator

import org.scalatest.funsuite.AsyncFunSuite

class OrchestratorTest extends AsyncFunSuite {

	test("Test Config") {
		Orchestrator().run() match {
			case Left(_) => fail
			case Right(_) => succeed
		}
	}
}
