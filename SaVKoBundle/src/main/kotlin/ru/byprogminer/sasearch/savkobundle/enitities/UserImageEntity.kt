package ru.byprogminer.sasearch.savkobundle.enitities

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "users_images")
@IdClass(UserImageEntity.PrimaryKey::class)
data class UserImageEntity(
        @Id val userId: String,
        @Id val imageId: String
) {

    class PrimaryKey(val userId: String, val imageId: String): Serializable {

        @Suppress("unused") constructor(): this("", "")
    }
}
