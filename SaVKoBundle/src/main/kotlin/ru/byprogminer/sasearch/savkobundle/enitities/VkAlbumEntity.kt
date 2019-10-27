package ru.byprogminer.sasearch.savkobundle.enitities

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "vk_albums")
@IdClass(VkAlbumEntity.PrimaryKey::class)
data class VkAlbumEntity(
        @Id val userId: Int,
        @Id val id: Int,
        val sync: Boolean = true
) {

    data class PrimaryKey(val userId: Int, val id: Int): Serializable {

        @Suppress("unused") constructor(): this(0, 0)
    }
}
