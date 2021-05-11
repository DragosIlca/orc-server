package com.uni.orc.converters

import com.uni.orc.models.Config.DockerCommandConfig
import com.uni.orc.models.parsed.Action.{CLICommand, DockerCommand}
import com.uni.orc.models.raw.{RawAction, RawTask}
import com.uni.orc.models.types.{Action, Hook}
import org.scalatest.funsuite.AsyncFunSuite

class ModelConverterTest extends AsyncFunSuite {
	val instruction = "instruction"

	test("CLI Command") {
		val rawAction = RawAction(Action.CLI_COMMAND, instruction, None)

		ModelConverter().convertAction(rawAction) match {
			case Left(_) => fail()
			case Right(action) =>
				assert(action.isInstanceOf[CLICommand])
				assert(action.instruction.equals(rawAction.instruction))
		}
	}

	test("Docker Command") {
		val rawAction = RawAction(Action.DOCKER_COMMAND, instruction, Some(DockerCommandConfig("", "")))

		ModelConverter().convertAction(rawAction) match {
			case Left(_) => fail
			case Right(action) =>
				assert(action.isInstanceOf[DockerCommand])
				assert(action.instruction.equals(rawAction.instruction))
		}
	}

	test("Docker Command - Config missing") {
		val rawAction = RawAction(Action.DOCKER_COMMAND, instruction, None)

		ModelConverter().convertAction(rawAction) match {
			case Left(_) => succeed
			case Right(_) => fail
		}
	}

	test("Task - Action failing") {
		val rawAction = RawAction(Action.HTTP_REQUEST, instruction, None)
		val rawTask = RawTask(Hook.INSTALL, rawAction)

		val eitherResultTask = ModelConverter().convertTask(rawTask)
		val eitherResultAction = ModelConverter().convertAction(rawAction)

		eitherResultTask match {
			case Right(_) => fail
			case Left(errTask) => eitherResultAction match {
				case Right(_) => fail
				case Left(errAction) => assert(errTask.equals(errAction))
			}
		}
	}
}
