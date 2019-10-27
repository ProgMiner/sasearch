package ru.byprogminer.sasearch

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import java.util.*

private const val VK_GROUP_ID = 188012899
private const val VK_GROUP_TOKEN = "4d41b6ec2d0c8475bb48d262be835c105d9e35259b510a475c4c723362ffb0298012d72cd8d9d42d59011"

fun main() {
    val transportClient = HttpTransportClient()
    val vk = VkApiClient(transportClient)

    val actor = GroupActor(VK_GROUP_ID, VK_GROUP_TOKEN)
    val longPoll = LongPollCallbackApi(object: SaVKoBundleConnection {

        override fun requestImages(userId: Int, keywords: List<String>): List<String> {
            val random = Random(keywords.hashCode().toLong() * userId)

            return listOf(
                    "photo-164517505_457294827",
                    "photo-184003532_457253354",
                    "photo-128637780_457309258",
                    "photo-152116137_457310512"
            ).filter { random.nextDouble() >= 0.5 }
        }

        override fun requestVideos(userId: Int, keywords: List<String>): List<String> = listOf()
    }, vk, actor)

    longPoll.run()
}
