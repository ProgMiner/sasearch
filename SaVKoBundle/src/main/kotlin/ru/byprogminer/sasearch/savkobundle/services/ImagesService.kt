package ru.byprogminer.sasearch.savkobundle.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.byprogminer.sasearch.savkobundle.api.v1.controllers.ImagesController
import ru.byprogminer.sasearch.savkobundle.enitities.ImageEntity
import ru.byprogminer.sasearch.savkobundle.repositories.ImagesRepository
import ru.byprogminer.sasearch.savkobundle.repositories.UsersImagesRepository
import java.util.*
import java.util.stream.Collectors

@Service
@Transactional
class ImagesService

@Autowired
constructor(
        private val repository: ImagesRepository,
        private val usersImagesRepository: UsersImagesRepository
) {

    fun getByUser(userId: String): List<ImageEntity> = usersImagesRepository
            .getByUserId(userId).stream().map { repository.findById(it.imageId) }
            .map(Optional<ImageEntity>::get).collect(Collectors.toList())!!

    operator fun get(id: String): ImageEntity = repository.findById(id).get()

    fun setTitle(id: String, title: String) {
        repository.save(get(id).copy(title = title))
    }

    fun addColor(id: String, color: String) {
        val image = get(id)

        repository.save(image.copy(colors = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.colors) + setOf(color))))
    }

    fun removeColor(id: String, color: String) {
        val image = get(id)

        repository.save(image.copy(colors = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.colors) - setOf(color))))
    }

    fun addTheme(id: String, theme: String) {
        val image = get(id)

        repository.save(image.copy(themes = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.themes) + setOf(theme))))
    }

    fun removeTheme(id: String, theme: String) {
        val image = get(id)

        repository.save(image.copy(themes = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.themes) - setOf(theme))))
    }

    fun addObject(id: String, `object`: String) {
        val image = get(id)

        repository.save(image.copy(objects = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.objects) + setOf(`object`))))
    }

    fun removeObject(id: String, `object`: String) {
        val image = get(id)

        repository.save(image.copy(objects = ImagesController.joinStringSet(ImagesController
                .parseStringSet(image.objects) - setOf(`object`))))
    }
}
