package com.superredstone.red_discord_presence.utils

import com.google.gson.JsonObject
import com.jagrosh.discordipc.IPCClient
import com.jagrosh.discordipc.IPCListener
import com.jagrosh.discordipc.entities.Packet
import com.jagrosh.discordipc.entities.RichPresence
import com.jagrosh.discordipc.entities.User
import com.jagrosh.discordipc.exceptions.NoDiscordClientException
import com.superredstone.red_discord_presence.RedDiscordPresence
import com.superredstone.red_discord_presence.config.ConfigHandler

class RichPresenceHandler(clientId: Long) {
    private val client: IPCClient = IPCClient(clientId)
    var startTime: Long = 0

    private fun init() {
        client.setListener(
                object : IPCListener {
                    override fun onPacketSent(client: IPCClient?, packet: Packet?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onPacketReceived(client: IPCClient?, packet: Packet?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onActivityJoin(client: IPCClient?, secret: String?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onActivitySpectate(client: IPCClient?, secret: String?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onActivityJoinRequest(
                            client: IPCClient?,
                            secret: String?,
                            user: User?
                    ) {
                        // TODO("Not yet implemented")
                    }

                    override fun onReady(client: IPCClient?) {
                        val cfg = ConfigHandler.readConfig()
                        val builder = RichPresence.Builder()
                        builder.setDetails("Main menu")
                                .setStartTimestamp(startTime)
                                .setLargeImage(cfg.menuImage, cfg.imageText)
                        client?.sendRichPresence(builder.build())
                    }

                    override fun onClose(client: IPCClient?, json: JsonObject?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onDisconnect(client: IPCClient?, t: Throwable?) {
                        // TODO("Not yet implemented")
                    }
                }
        )
    }

    fun setStatus(
            details: String,
            state: String,
            icon: String,
            iconName: String,
            partySize: Int = 0,
            partyMax: Int = 0,
            partyPrivacy: Int = 0
    ) {
        val builder =
                RichPresence.Builder()
                        .setDetails(details)
                        .setState(state)
                        .setLargeImage(icon, iconName)
                        .setParty("", partySize, partyMax, partyPrivacy)
                        .setStartTimestamp(startTime)

        client.sendRichPresence(builder.build())
    }

    fun connect() {
        this.init()
        try {
            client.connect()
        } catch (err: NoDiscordClientException) {
            RedDiscordPresence.LOGGER.error("Unable to connect to Discord")
        }
    }
}

