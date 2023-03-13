package com.tsu.dailyschedule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tsu.dailyschedule.databinding.EmptyLessonItemBinding
import com.tsu.dailyschedule.databinding.LessonItemBinding
import com.tsu.dailyschedule.domain.entity.DailyItem
import com.tsu.dailyschedule.domain.entity.EmptyLessonItem
import com.tsu.dailyschedule.domain.entity.LessonItem
import com.tsu.dailyschedule.ui.ColorBundle
import com.tsu.shared.entity.Lesson

class ScheduleAdapter(
	private val clickListener: (Lesson) -> Unit, private val getColors: (String) -> ColorBundle, private val gestureDetector: GestureDetectorCompat
) : RecyclerView.Adapter<ViewHolder>() {

	var data: List<DailyItem> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	companion object {

		const val EMPTY = 1
		const val LESSON = 0
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			LESSON -> LessonItemViewHolder(LessonItemBinding.inflate(inflater, parent, false))
			else   -> EmptyLessonItemViewHolder(EmptyLessonItemBinding.inflate(inflater, parent, false))
		}
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		if (data[position] is LessonItem) (holder as LessonItemViewHolder).bind(data[position] as LessonItem, clickListener, getColors, gestureDetector)
		else (holder as EmptyLessonItemViewHolder).bind(data[position] as EmptyLessonItem, gestureDetector)
	}

	override fun getItemViewType(position: Int): Int {
		return when (data[position]) {
			is LessonItem -> LESSON
			else          -> EMPTY
		}
	}

	override fun getItemCount() = data.size

	class EmptyLessonItemViewHolder(private val binding: EmptyLessonItemBinding) : ViewHolder(binding.root) {

		fun bind(data: EmptyLessonItem, gestureDetector: GestureDetectorCompat) {
			val timeText = "${data.startTime} - ${data.endTime}"
			binding.timeTextView.text = timeText

			binding.root.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
		}
	}

	class LessonItemViewHolder(private val binding: LessonItemBinding) : ViewHolder(binding.root) {

		fun bind(item: LessonItem, clickListener: (Lesson) -> Unit, getColors: (String) -> ColorBundle, gestureDetector: GestureDetectorCompat) {
			with(binding) {
				val lesson = item.lesson
				subjectTextView.text = lesson.name.name
				groupTextView.text = lesson.group[0].name
				teacherTextView.text = lesson.teacher.name
				classroomTextView.text = lesson.classroom.name

				val timeText = "${lesson.timeInterval.startTime} - ${lesson.timeInterval.endTime}"
				timeTextView.text = timeText

				val colors = getColors(lesson.tag.name)
				view.setBackgroundColor(colors.background)

				subjectTextView.setTextColor(colors.text)
				groupTextView.setTextColor(colors.text)
				teacherTextView.setTextColor(colors.text)
				classroomTextView.setTextColor(colors.text)
				timeTextView.setTextColor(colors.text)

				binding.root.setOnClickListener { clickListener(lesson) }
				binding.root.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
			}
		}
	}
}