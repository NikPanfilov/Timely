package com.tsu.timely.di

import com.tsu.scheduleselection.data.api.ClassroomSearchApi
import com.tsu.scheduleselection.data.api.GroupSearchApi
import com.tsu.scheduleselection.data.api.TeacherSearchApi
import com.tsu.scheduleselection.data.datasource.ClassroomSearchDataSource
import com.tsu.scheduleselection.data.datasource.ClassroomSearchDataSourceImpl
import com.tsu.scheduleselection.data.datasource.GroupSearchDataSource
import com.tsu.scheduleselection.data.datasource.GroupSearchDataSourceImpl
import com.tsu.scheduleselection.data.datasource.TeacherSearchDataSource
import com.tsu.scheduleselection.data.datasource.TeacherSearchDataSourceImpl
import com.tsu.scheduleselection.data.repository.ClassroomSearchRepositoryImpl
import com.tsu.scheduleselection.data.repository.GroupSearchRepositoryImpl
import com.tsu.scheduleselection.data.repository.TeacherSearchRepositoryImpl
import com.tsu.scheduleselection.domain.repository.ClassroomSearchRepository
import com.tsu.scheduleselection.domain.repository.GroupSearchRepository
import com.tsu.scheduleselection.domain.repository.TeacherSearchRepository
import com.tsu.scheduleselection.domain.usecase.SearchClassroomUseCase
import com.tsu.scheduleselection.domain.usecase.SearchGroupUseCase
import com.tsu.scheduleselection.domain.usecase.SearchTeacherUseCase
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.shared.ScheduleType
import com.tsu.shared.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleSelectionModule = module {
	viewModel { (type: ScheduleType) ->
		ScheduleSelectionViewModel(
			router = get(),
			scheduleType = type,
			searchGroup = get(),
			searchTeacher = get(),
			searchClassroom = get()
		)
	}

	factory { createRetrofitService<GroupSearchApi>(get()) }
	factory { createRetrofitService<TeacherSearchApi>(get()) }
	factory { createRetrofitService<ClassroomSearchApi>(get()) }

	single<GroupSearchDataSource> { GroupSearchDataSourceImpl(get()) }
	single<TeacherSearchDataSource> { TeacherSearchDataSourceImpl(get()) }
	single<ClassroomSearchDataSource> { ClassroomSearchDataSourceImpl(get()) }

	factory<GroupSearchRepository> { GroupSearchRepositoryImpl(get()) }
	factory<TeacherSearchRepository> { TeacherSearchRepositoryImpl(get()) }
	factory<ClassroomSearchRepository> { ClassroomSearchRepositoryImpl(get()) }

	factory { SearchGroupUseCase(get()) }
	factory { SearchTeacherUseCase(get()) }
	factory { SearchClassroomUseCase(get()) }
}