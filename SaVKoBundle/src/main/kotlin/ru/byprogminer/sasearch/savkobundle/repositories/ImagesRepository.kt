package ru.byprogminer.sasearch.savkobundle.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.byprogminer.sasearch.savkobundle.enitities.ImageEntity

interface ImagesRepository: JpaRepository<ImageEntity, String>
