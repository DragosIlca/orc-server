package com.uni.orc.converters

import com.uni.orc.models.Config.DockerCommandConfig
import com.uni.orc.models.parsed.Action.{CLICommand, DockerCommand}
import com.uni.orc.models.raw.RawAction
import com.uni.orc.types.Action
import org.scalatest.funsuite.AsyncFunSuite

class ModelConverterTest extends AsyncFunSuite {
	test("CLI Command") {
		val instruction = "instruction"

		val rawAction = RawAction(Action.CLI_COMMAND, instruction, None)

		ModelConverter.convertAction(rawAction) match {
			case Left(_) => fail()
			case Right(action) =>
				assert(action.isInstanceOf[CLICommand])
				assert(action.instruction.equals(rawAction.instruction))
		}
	}

	test("Docker Command") {
		val instruction = "instruction"

		val rawAction = RawAction(Action.DOCKER_COMMAND, instruction, Some(DockerCommandConfig("", "")))

		ModelConverter.convertAction(rawAction) match {
			case Left(_) => fail
			case Right(action) =>
				assert(action.isInstanceOf[DockerCommand])
				assert(action.instruction.equals(rawAction.instruction))
		}
	}

	test("Docker Command - Config missing") {
		val instruction = "instruction"

		val rawAction = RawAction(Action.DOCKER_COMMAND, instruction, None)

		ModelConverter.convertAction(rawAction) match {
			case Left(_) => succeed
			case Right(_) => fail
		}
	}
}
