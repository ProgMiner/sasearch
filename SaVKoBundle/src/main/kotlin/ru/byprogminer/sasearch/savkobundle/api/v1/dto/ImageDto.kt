package ru.byprogminer.sasearch.savkobundle.api.v1.dto

data class ImageDto(
        val id: String,
        val title: String,
        val colors: Set<String>,
        val themes: Set<String>,
        val objects: Set<String>
)
