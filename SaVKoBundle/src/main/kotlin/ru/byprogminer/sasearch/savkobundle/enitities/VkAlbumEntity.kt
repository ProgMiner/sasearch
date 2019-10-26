package ru.byprogminer.sasearch.savkobundle.enitities

import java.io.Serializable
import javax.persistence.*

@Entity(name = "vk_albums")
@IdClass(VkAlbumEntity.PrimaryKey::class)
data class VkAlbumEntity(
        @Id val userId: Int,
        @Id val albumId: Int,
        val sync: Boolean = true
) {

    data class PrimaryKey(val userId: Int, val albumId: Int): Serializable {

        @Suppress("unused") constructor(): this(0, 0)
    }
}
