package ru.byprogminer.sasearch.savkobundle.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ru.byprogminer.sasearch.savkobundle.enitities.VkAlbumEntity

interface VkAlbumsRepository: JpaRepository<VkAlbumEntity, VkAlbumEntity.PrimaryKey> {

    fun getByUserId(userId: Int): List<VkAlbumEntity>
}
