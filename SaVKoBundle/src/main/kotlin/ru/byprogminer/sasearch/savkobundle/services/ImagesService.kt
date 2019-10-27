package ru.byprogminer.sasearch.savkobundle.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.byprogminer.sasearch.savkobundle.api.v1.controllers.ImagesController
import ru.byprogminer.sasearch.savkobundle.enitities.ImageEntity
import ru.byprogminer.sasearch.savkobundle.repositories.ImagesRepository
import ru.byprogminer.sasearch.savkobundle.repositories.UsersImagesRepository
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors
import javax.imageio.ImageIO

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

    fun add(userId: String, image: MultipartFile): String {
        val id = UUID.randomUUID().toString()

        val path = Paths.get(ImagesController.imagePath(id));
        Files.createDirectories(path.parent)

        ImageIO.write(ImageIO.read(image.inputStream), "JPEG", path.toFile())
        repository.save(ImageEntity(id, "", "", "", ""))

        // TODO work with hash
        // TODO auto-set title, colors, theme, objects

        return id
    }

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
