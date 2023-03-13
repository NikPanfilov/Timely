package com.tsu.details.ui

import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tsu.details.R
import com.tsu.details.databinding.FragmentDetailsBinding
import com.tsu.details.presentation.DetailsViewModel
import com.tsu.details.ui.adapter.GroupsAdapter
import com.tsu.shared.CLASSROOMS
import com.tsu.shared.TEACHERS

internal fun FragmentDetailsBinding.bindData(viewModel: DetailsViewModel, listAdapter: GroupsAdapter) {
	with(viewModel.lesson) {
		listAdapter.data = group
		subjectTextView.text = name.name
		typeTextView.text = tag.name
		typeTextView.setColor()
		classroomTextView.text = classroom.name
		teacherTextView.text = teacher.name

		val time = timeInterval.startTime + " : " + timeInterval.endTime
		timeIntervalTextView.text = time

		classroomTextView.setOnClickListener { viewModel.navigateToDailySchedule(classroom.id!!, CLASSROOMS) }
		teacherTextView.setOnClickListener { viewModel.navigateToDailySchedule(teacher.id!!, TEACHERS) }
	}
}

private fun TextView.setColor() {
	when (text) {
		resources.getString(R.string.laboratory) -> {
			(background as GradientDrawable).setColor(ContextCompat.getColor(context, R.color.laboratory))
			setTextColor(ContextCompat.getColor(context, R.color.laboratory_text))
		}

		resources.getString(R.string.lecture)    -> {
			(background as GradientDrawable).setColor(ContextCompat.getColor(context, R.color.lecture))
			setTextColor(ContextCompat.getColor(context, R.color.lecture_text))
		}

		resources.getString(R.string.practise)   -> {
			(background as GradientDrawable).setColor(ContextCompat.getColor(context, R.color.practise))
			setTextColor(ContextCompat.getColor(context, R.color.practise_text))
		}

		resources.getString(R.string.seminar)    -> {
			(background as GradientDrawable).setColor(ContextCompat.getColor(context, R.color.seminar))
			setTextColor(ContextCompat.getColor(context, R.color.seminar_text))
		}

		else                                     -> {
			(background as GradientDrawable).setColor(ContextCompat.getColor(context, R.color.exam))
			setTextColor(ContextCompat.getColor(context, R.color.exam_text))
		}
	}
}