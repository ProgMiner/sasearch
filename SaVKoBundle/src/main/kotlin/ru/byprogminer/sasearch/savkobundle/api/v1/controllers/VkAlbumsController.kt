package ru.byprogminer.sasearch.savkobundle.api.v1.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.byprogminer.sasearch.savkobundle.api.v1.dto.VkAlbumDto
import ru.byprogminer.sasearch.savkobundle.services.VkAlbumsService
import ru.byprogminer.sasearch.savkobundle.tryToBoolean

@RestController
class VkAlbumsController

@Autowired
constructor(private val service: VkAlbumsService) {

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/get/user/{userId}"])
    fun getByUser(@PathVariable userId: Int) = service[userId].map { VkAlbumDto(it.userId, it.id, it.sync) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/add/user/{userId}/album/{id}"])
    fun addToUser(@PathVariable userId: Int, @PathVariable id: Int) =
            tryToBoolean<Throwable> { service.add(userId, id) }

    @RequestMapping(method = [RequestMethod.GET], path = ["/api/v1/vk/albums/remove/user/{userId}/album/{id}"])
    fun removeFromUser(@PathVariable userId: Int, @PathVariable id: Int) =
            tryToBoolean<Throwable> { service.remove(userId, id) }

    @RequestMapping(method = [RequestMethod.GET],
            path = ["/api/v1/vk/albums/set/user/{userId}/album/{id}/synchronization/{sync}"])
    fun setSynchronization(
            @PathVariable userId: Int,
            @PathVariable id: Int,
            @PathVariable sync: Boolean
    ) = tryToBoolean<Throwable> { service.setSynchronization(userId, id, sync) }
}
