package com.uni.orc

import com.uni.orc.orchestrator.Orchestrator

object Main {
	def main(args: Array[String]): Unit = {
		Orchestrator().run().unsafeRunSync()
	}
}
