package com.uni.orc.models.enums

object Action {
  val CLI_COMMAND: ActionType = "cli"
  val DOCKER_COMMAND: ActionType = "docker"
  val HTTP_REQUEST: ActionType = "request"

  type ActionType = String
}
