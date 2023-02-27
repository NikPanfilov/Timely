package com.tsu.shared

data class ScheduleType(val type: Type) : java.io.Serializable

sealed class Type {
	object Group : Type()
	object Teacher : Type()
	object Classroom : Type()
}
