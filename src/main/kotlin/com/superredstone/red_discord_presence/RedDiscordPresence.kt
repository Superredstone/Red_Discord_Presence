package com.superredstone.red_discord_presence

import com.superredstone.red_discord_presence.config.ConfigHandler
import com.superredstone.red_discord_presence.events.PlayerDisconnectHandler
import com.superredstone.red_discord_presence.events.PlayerJoinHandler
import com.superredstone.red_discord_presence.utils.RichPresenceHandler
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import java.time.OffsetDateTime

@Suppress("UNUSED")
object RedDiscordPresence: ModInitializer {
    val richPresenceHandler = RichPresenceHandler(ConfigHandler.validateConfig())

    override fun onInitialize() {
        val config = ConfigHandler.readConfig()

        // Register events
        ClientPlayConnectionEvents.JOIN.register(PlayerJoinHandler())
        ClientPlayConnectionEvents.DISCONNECT.register(PlayerDisconnectHandler())

        // Start rich presence
        if (config.enableRichPresence == true) {
            richPresenceHandler.startTime = OffsetDateTime.now().toEpochSecond()
            richPresenceHandler.connect()
        }
    }
}
