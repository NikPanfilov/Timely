package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.shared.schedule.data.api.AudienceApi
import com.tsu.shared.schedule.data.api.BookApi
import com.tsu.shared.schedule.data.api.GroupsApi
import com.tsu.shared.schedule.data.api.ScheduleApi
import com.tsu.shared.schedule.data.datasource.ScheduleDataSource
import com.tsu.shared.schedule.data.datasource.ScheduleDataSourceImpl
import com.tsu.shared.schedule.data.repository.AudienceRepositoryImpl
import com.tsu.shared.schedule.data.repository.BookRepositoryImpl
import com.tsu.shared.schedule.data.repository.GroupsRepositoryImpl
import com.tsu.shared.schedule.data.repository.ScheduleRepositoryImpl
import com.tsu.shared.schedule.domain.repository.AudienceRepository
import com.tsu.shared.schedule.domain.repository.BookRepository
import com.tsu.shared.schedule.domain.repository.GroupsRepository
import com.tsu.shared.schedule.domain.repository.ScheduleRepository
import com.tsu.shared.schedule.domain.usecase.BookAudienceUseCase
import com.tsu.shared.schedule.domain.usecase.GetAudiencesUseCase
import com.tsu.shared.schedule.domain.usecase.GetDailyScheduleUseCase
import com.tsu.shared.schedule.domain.usecase.GetGroupsUseCase
import com.tsu.shared.schedule.domain.usecase.GetWeeklyScheduleUseCase
import org.koin.dsl.module

val bookingModule = module {
	factory { createRetrofitService<AudienceApi>(get()) }
	factory { createRetrofitService<BookApi>(get()) }
	factory { createRetrofitService<GroupsApi>(get()) }
	factory { createRetrofitService<ScheduleApi>(get()) }

	single<ScheduleDataSource> { ScheduleDataSourceImpl(get()) }

	factory<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
	factory<AudienceRepository> { AudienceRepositoryImpl(get()) }
	factory<BookRepository> { BookRepositoryImpl(get()) }
	factory<GroupsRepository> { GroupsRepositoryImpl(get()) }

	factory { GetDailyScheduleUseCase(get()) }
	factory { GetWeeklyScheduleUseCase(get()) }
	factory { BookAudienceUseCase(get()) }
	factory { GetAudiencesUseCase(get()) }
	factory { GetGroupsUseCase(get()) }
}