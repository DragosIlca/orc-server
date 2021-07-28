package com.uni.orc.models

case class ProjectPlugin(
	id         : String,
	name       : String,
	description: String,
	version    : String,
	lifecycle  : List[Task],
	execution  : List[Command]
)
