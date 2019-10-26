package ru.byprogminer.sasearch

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.queries.messages.MessagesSendQuery
import java.util.stream.Collectors
import kotlin.random.Random

class LongPollCallbackApi: CallbackApiLongPoll {

    private val saVKoBundle: SaVKoBundleConnection
    private val actor: GroupActor

    constructor(saVKoBundle: SaVKoBundleConnection, client: VkApiClient, actor: GroupActor): super(client, actor) {
        this.saVKoBundle = saVKoBundle
        this.actor = actor
    }

    constructor(saVKoBundle: SaVKoBundleConnection, client: VkApiClient, actor: GroupActor, waitTime: Int):
            super(client, actor, waitTime)
    {
        this.saVKoBundle = saVKoBundle;
        this.actor = actor
    }

    override fun messageNew(groupId: Int?, message: Message?) {
        if (groupId == null || message == null) {
            return
        }

        sendMessage(message.peerId)
                .message("Принято к обработке, ждите...")
                .execute()

        handleMessage(message.text, message.peerId)
    }

    private fun handleMessage(message: String, userId: Int) {
        // TODO

        val keywords = message.split(',').parallelStream()
                .map(String::trim).collect(Collectors.toList())

        val images = saVKoBundle.requestImages(userId, keywords)

        if (images.isEmpty()) {
            sendMessage(userId)
                    .message("Ни одной подходящей картинки не найдено :(")
                    .execute()
        } else {
            sendMessage(userId)
                    .attachment(images.parallelStream().limit(10)
                            .collect(Collectors.joining(",")))
                    .execute()
        }
    }

    private fun sendMessage(userId: Int): MessagesSendQuery = client.messages().send(actor)
            .peerId(userId).randomId(Random(System.currentTimeMillis()).nextInt())
}
