package ru.byprogminer.sasearch

interface SaVKoBundleConnection {

    fun requestImages(userId: Int, keywords: List<String>): List<String>
    fun requestVideos(userId: Int, keywords: List<String>): List<String>
}
