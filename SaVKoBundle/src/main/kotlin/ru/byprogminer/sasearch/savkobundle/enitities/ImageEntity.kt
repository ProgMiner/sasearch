package ru.byprogminer.sasearch.savkobundle.enitities

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "images")
data class ImageEntity(@Id val id: String, val hash: String)
