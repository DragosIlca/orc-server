package com.uni.orc.models

import Action.Action
import com.uni.orc.models.enums.Hook.HookType

case class Task(
	hookType: HookType,
	action  : Action
)
