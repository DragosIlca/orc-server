package com.uni.orc.models.raw

import com.uni.orc.models.common.Command

case class RawProjectPlugin(
	id         : String,
	name       : String,
	description: String,
	version    : String,
	lifecycle  : List[RawTask],
	execution  : List[Command]
)
