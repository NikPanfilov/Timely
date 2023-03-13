package com.tsu.dailyschedule.ui

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.tsu.dailyschedule.presentation.DailyScheduleViewModel

class GestureListener(private val viewModel: DailyScheduleViewModel) : GestureDetector.OnGestureListener {

	override fun onDown(p0: MotionEvent?): Boolean {
		return true
	}

	override fun onShowPress(p0: MotionEvent?) {
	}

	override fun onSingleTapUp(p0: MotionEvent?): Boolean {
		return true
	}

	override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
		return true
	}

	override fun onLongPress(p0: MotionEvent?) {
	}

	override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
		if (p1!!.x > p0!!.x ) {
			// Свайп вправо
			viewModel.date = viewModel.date.minusDays(1)
			viewModel.getSchedule()
			Log.i("swipe", "Right")
		}

		if (p0.x > p1.x) {
			// Свайп влево
			viewModel.date = viewModel.date.plusDays(1)
			viewModel.getSchedule()
			Log.i("swipe", "Left")
		}
		return true
	}
}