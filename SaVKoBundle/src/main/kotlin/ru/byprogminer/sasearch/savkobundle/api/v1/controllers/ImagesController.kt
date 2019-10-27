package ru.byprogminer.sasearch.savkobundle.api.v1.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.byprogminer.sasearch.savkobundle.api.v1.dto.ImageDto
import ru.byprogminer.sasearch.savkobundle.services.ImagesService
import ru.byprogminer.sasearch.savkobundle.tryToBoolean
import java.io.FileInputStream

@RestController
class ImagesController

@Autowired
constructor(private val service: ImagesService) {

    companion object {

        private const val IMAGES_PATH = "images/"
        private const val HASHES_PATH = "hashes/"

        internal fun parseStringSet(str: String) = str.split(",").map(String::trim).filter(String::isNotBlank).toSet()
        internal fun joinStringSet(set: Collection<String>) = set.joinToString(",", transform = String::trim)

        internal fun imagePath(id: String) = IMAGES_PATH + id.replace('-', '/') + ".jpg"
        internal fun hashPath(id: String) = HASHES_PATH + id.replace('-', '/') + ".jpg"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/get/user/{userId}"])
    fun getByUser(@PathVariable userId: String) = service.getByUser(userId).map { entity ->
        ImageDto(entity.id, entity.title, parseStringSet(entity.colors),
                parseStringSet(entity.themes), parseStringSet(entity.objects)) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/get/image/{id}"])
    fun get(@PathVariable id: String) = try {
        service[id].let { ImageDto(it.id, it.title, parseStringSet(it.colors),
                parseStringSet(it.themes), parseStringSet(it.objects)) }
    } catch (e: Throwable) {
        e.printStackTrace()
        "null"
    }

    @RequestMapping(
            method = [RequestMethod.GET],
            path = ["/api/v1/images/load/image/{id}"],
            produces = [MediaType.TEXT_PLAIN_VALUE, MediaType.IMAGE_JPEG_VALUE]
    )
    fun load(@PathVariable id: String) = try {
        FileInputStream(imagePath(id)).readBytes()
    } catch (e: Throwable) {
        e.printStackTrace()
        "null"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/set/image/{id}/title/{title}"])
    fun setTitle(@PathVariable id: String, @PathVariable title: String) =
            tryToBoolean<Throwable> { service.setTitle(id, title) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/add/image/{id}/color/{color}"])
    fun addColor(@PathVariable id: String, @PathVariable color: String) =
            tryToBoolean<Throwable> { service.addColor(id, color) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/remove/image/{id}/color/{color}"])
    fun removeColor(@PathVariable id: String, @PathVariable color: String) =
            tryToBoolean<Throwable> { service.removeColor(id, color) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/add/image/{id}/theme/{theme}"])
    fun addTheme(@PathVariable id: String, @PathVariable theme: String) =
            tryToBoolean<Throwable> { service.addTheme(id, theme) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/remove/image/{id}/theme/{theme}"])
    fun removeTheme(@PathVariable id: String, @PathVariable theme: String) =
            tryToBoolean<Throwable> { service.removeTheme(id, theme) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/add/image/{id}/object/{object}"])
    fun addObject(@PathVariable id: String, @PathVariable `object`: String) =
            tryToBoolean<Throwable> { service.addObject(id, `object`) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/images/remove/image/{id}/object/{object}"])
    fun removeObject(@PathVariable id: String, @PathVariable `object`: String) =
            tryToBoolean<Throwable> { service.removeObject(id, `object`) }
}
