package ru.byprogminer.sasearch.savkobundle.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.byprogminer.sasearch.savkobundle.enitities.ImageEntity

interface ImagesRepository: JpaRepository<ImageEntity, String> {

    @Query("""select * from images i
        join users_images ui on (i.id = ui.image_id)
        where ui.user_id = :userId and
        (i.title ilike :keyword or
        i.colors ilike :keyword or
        i.themes ilike :keyword or
        i.objects ilike :keyword)""",
            nativeQuery = true)
    fun search(@Param("userId") userId: String, @Param("keyword") keyword: String): List<ImageEntity>
}
