package ru.byprogminer.sasearch.savkobundle.enitities

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "images")
data class ImageEntity(
        @Id val id: String,
        val title: String,
        val colors: String,
        val themes: String,
        val objects: String
)
