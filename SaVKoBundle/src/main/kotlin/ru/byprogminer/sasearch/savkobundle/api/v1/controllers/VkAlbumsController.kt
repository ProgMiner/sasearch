package ru.byprogminer.sasearch.savkobundle.api.v1.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.byprogminer.sasearch.savkobundle.api.v1.dto.VkAlbumDto
import ru.byprogminer.sasearch.savkobundle.services.VkAlbumsService

@RestController
class VkAlbumsController

@Autowired
constructor(private val service: VkAlbumsService) {

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/get/user/{userId}"])
    fun getByUser(@PathVariable userId: Int) = service[userId].map { VkAlbumDto(it.userId, it.albumId, it.sync) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/add/user/{userId}/album/{albumId}"])
    fun addToUser(@PathVariable userId: Int, @PathVariable albumId: Int) =
            try {
                service.add(userId, albumId)
                true
            } catch (e: Throwable) {
                e.printStackTrace()
                false
            }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/remove/user/{userId}/album/{albumId}"])
    fun removeFromUser(@PathVariable userId: Int, @PathVariable albumId: Int) =
            try {
                service.remove(userId, albumId)
                true
            } catch (e: Throwable) {
                e.printStackTrace()
                false
            }

    @RequestMapping(method = [RequestMethod.GET],
            path = ["/api/v1/vk/albums/set/user/{userId}/album/{albumId}/synchronization/{sync}"])
    fun setSynchronization(
            @PathVariable userId: Int,
            @PathVariable albumId: Int,
            @PathVariable sync: Boolean
    ) = try {
        service.setSynchronization(userId, albumId, sync)
        true
    } catch (e: Throwable) {
        e.printStackTrace()
        false
    }
}
