package com.tsu.timely.di

import com.tsu.details.presentation.DetailsViewModel
import com.tsu.shared.navigation.holder.LessonHolder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
	viewModel { (lessonHolder: LessonHolder) -> DetailsViewModel(router = get(), lessonHolder = lessonHolder) }
}