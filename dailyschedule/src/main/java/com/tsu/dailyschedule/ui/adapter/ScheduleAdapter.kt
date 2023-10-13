package com.tsu.dailyschedule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tsu.dailyschedule.databinding.BookItemBinding
import com.tsu.dailyschedule.databinding.BreakItemBinding
import com.tsu.dailyschedule.databinding.LessonItemBinding
import com.tsu.dailyschedule.domain.entity.BookedItem
import com.tsu.dailyschedule.domain.entity.BreakItem
import com.tsu.dailyschedule.domain.entity.DailyItem
import com.tsu.dailyschedule.domain.entity.LessonItem
import com.tsu.dailyschedule.ui.ColorBundle
import com.tsu.shared.entity.TimeSlot

class ScheduleAdapter(
	private val lessonClickListener: (TimeSlot.Lesson) -> Unit,
	private val breakClickListener: (Int) -> Unit,
	private val getColors: (String) -> ColorBundle,
	private val gestureDetector: GestureDetectorCompat,
) : RecyclerView.Adapter<ViewHolder>() {

	var data: List<DailyItem> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	companion object {

		const val LESSON = 1
		const val BREAK = 2
		const val STATIC_BREAK = 3
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			LESSON -> LessonItemViewHolder(LessonItemBinding.inflate(inflater, parent, false))
			BREAK  -> BreakItemViewHolder(BreakItemBinding.inflate(inflater, parent, false))
			else   -> BookItemViewHolder(BookItemBinding.inflate(inflater, parent, false))
		}
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		when (data[position]) {
			is LessonItem -> (holder as LessonItemViewHolder).bind(data[position] as LessonItem, lessonClickListener, getColors, gestureDetector)
			is BreakItem  -> (holder as BreakItemViewHolder).bind(data[position] as BreakItem, breakClickListener, gestureDetector)
			is BookedItem -> (holder as BookItemViewHolder).bind(data[position] as BookedItem, gestureDetector)
		}

	}

	override fun getItemViewType(position: Int): Int = when (data[position]) {
		is LessonItem -> LESSON
		is BreakItem  -> BREAK
		else          -> STATIC_BREAK
	}

	override fun getItemCount() = data.size

	class LessonItemViewHolder(
		private val binding: LessonItemBinding
	) : ViewHolder(binding.root) {

		fun bind(item: LessonItem, clickListener: (TimeSlot.Lesson) -> Unit, getColors: (String) -> ColorBundle, gestureDetector: GestureDetectorCompat) {
			with(binding) {
				val lesson = item.lesson
				subjectTextView.text = lesson.title
				groupTextView.text = lesson.groups.first().name
				teacherTextView.text = lesson.professor.shortName
				classroomTextView.text = lesson.audience.name

				val timeText = "${lesson.starts} - ${lesson.ends}"
				timeTextView.text = timeText

				val colors = getColors(lesson.type)
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

	class BreakItemViewHolder(
		private val binding: BreakItemBinding
	) : ViewHolder(binding.root) {

		fun bind(item: BreakItem, clickListener: (Int) -> Unit, gestureDetector: GestureDetectorCompat) {
			with(binding) {
				val timeText = "${item.breakItem.starts} - ${item.breakItem.ends}"
				timeTextView.text = timeText

				binding.bookButton.setOnClickListener { clickListener(item.breakItem.timeSlot) }
				binding.root.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
			}
		}
	}

	class BookItemViewHolder(
		private val binding: BookItemBinding
	) : ViewHolder(binding.root) {

		fun bind(item: BookedItem, gestureDetector: GestureDetectorCompat) {
			with(binding) {
				val timeText = "${item.booked.starts} - ${item.booked.ends}"
				timeTextView.text = timeText
				bookTitleTextView.text = item.booked.description

				binding.root.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
			}
		}
	}
}