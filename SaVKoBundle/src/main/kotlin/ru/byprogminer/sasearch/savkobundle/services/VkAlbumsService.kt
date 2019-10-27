package ru.byprogminer.sasearch.savkobundle.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.byprogminer.sasearch.savkobundle.enitities.VkAlbumEntity
import ru.byprogminer.sasearch.savkobundle.repositories.VkAlbumsRepository

@Service
@Transactional
class VkAlbumsService

@Autowired
constructor(private val repository: VkAlbumsRepository) {

    operator fun get(userId: Int) = repository.getByUserId(userId)

    fun add(userId: Int, id: Int) {
        if (repository.existsById(VkAlbumEntity.PrimaryKey(userId, id))) {
            throw RuntimeException("Album is already exists")
        }

        repository.save(VkAlbumEntity(userId, id))
    }

    fun remove(userId: Int, id: Int) {
        if (!repository.existsById(VkAlbumEntity.PrimaryKey(userId, id))) {
            throw RuntimeException("Album has not exists yet")
        }

        repository.delete(VkAlbumEntity(userId, id))
    }

    fun setSynchronization(userId: Int, id: Int, sync: Boolean) {
        if (!repository.existsById(VkAlbumEntity.PrimaryKey(userId, id))) {
            throw RuntimeException("Album has not exists yet")
        }

        repository.save(VkAlbumEntity(userId, id, sync))

        // TODO start indexation
    }
}
