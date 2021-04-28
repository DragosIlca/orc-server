package com.uni.orc.models.parsed

import com.uni.orc.models.parsed.Action.Action
import com.uni.orc.models.Config._
import com.uni.orc.types.Hook.HookType

case class Task[D <: Config](hookType: HookType, action: Action[D])