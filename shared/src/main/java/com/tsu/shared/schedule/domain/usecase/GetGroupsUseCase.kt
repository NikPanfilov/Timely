package com.tsu.shared.schedule.domain.usecase

import com.tsu.shared.entity.Group
import com.tsu.shared.schedule.domain.repository.GroupsRepository

class GetGroupsUseCase(private val repository: GroupsRepository) {

	suspend operator fun invoke(): List<Group> = repository.getGroups()
}