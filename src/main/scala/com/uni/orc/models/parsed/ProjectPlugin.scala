package com.uni.orc.models.parsed

import com.uni.orc.models.common.Command

case class ProjectPlugin(
	id         : String,
	name       : String,
	description: String,
	version    : String,
	lifecycle  : List[Task],
	execution  : List[Command]
)
