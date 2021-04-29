package com.uni.orc.models.raw

import com.uni.orc.models.types.Hook.HookType

case class RawTask(hook: HookType, action: RawAction)