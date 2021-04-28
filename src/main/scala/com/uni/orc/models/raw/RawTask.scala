package com.uni.orc.models.raw

import com.uni.orc.types.Hook.HookType

case class RawTask(hook: HookType, action: RawAction)