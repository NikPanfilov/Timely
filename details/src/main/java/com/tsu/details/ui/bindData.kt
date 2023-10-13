package com.tsu.details.ui

import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tsu.details.R
import com.tsu.details.databinding.FragmentDetailsBinding
import com.tsu.details.presentation.DetailsViewModel
import com.tsu.details.ui.adapter.GroupsAdapter
import com.tsu.shared.AUDIENCES
import com.tsu.shared.PROFESSORS

internal fun FragmentDetailsBinding.bindData(viewModel: DetailsViewModel, listAdapter: GroupsAdapter) {
	with(viewModel.lesson) {
		listAdapter.data = groups
		subjectTextView.text = title
		typeTextView.text = type
		typeTextView.setColor()
		classroomTextView.text = audience.name
		teacherTextView.text = professor.fullName

		val time = "$starts : $ends"
		timeIntervalTextView.text = time

		classroomTextView.setOnClickListener { viewModel.navigateToDailySchedule(audience.id!!, AUDIENCES) }
		teacherTextView.setOnClickListener { viewModel.navigateToDailySchedule(professor.id, PROFESSORS) }
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