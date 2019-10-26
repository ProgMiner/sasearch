package ru.byprogminer.sasearch.savkobundle.enitities

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "users")
data class UserEntity(@Id val id: String, val vk: String?)
