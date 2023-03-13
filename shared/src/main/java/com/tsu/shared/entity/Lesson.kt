package com.tsu.shared.entity

import java.time.LocalDate

data class Lesson(
	val name: Name,
	val tag: Tag,
	val group: List<Group>,
	val teacher: Teacher,
	val timeInterval: TimeInterval,
	val date: LocalDate,
	val classroom: Classroom,
	val id: String?
) : java.io.Serializable
