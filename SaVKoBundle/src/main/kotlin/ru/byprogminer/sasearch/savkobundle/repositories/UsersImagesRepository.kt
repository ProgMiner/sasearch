package ru.byprogminer.sasearch.savkobundle.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.byprogminer.sasearch.savkobundle.enitities.UserImageEntity

interface UsersImagesRepository: JpaRepository<UserImageEntity, UserImageEntity.PrimaryKey> {

    fun getByUserId(userId: String): List<UserImageEntity>
}
