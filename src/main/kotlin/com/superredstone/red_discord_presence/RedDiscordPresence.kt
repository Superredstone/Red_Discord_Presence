package com.superredstone.red_discord_presence

import com.superredstone.red_discord_presence.config.ConfigHandler
import com.superredstone.red_discord_presence.events.PlayerDisconnectHandler
import com.superredstone.red_discord_presence.events.PlayerJoinHandler
import com.superredstone.red_discord_presence.utils.RichPresenceHandler
import java.time.OffsetDateTime
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import org.slf4j.LoggerFactory

@Suppress("UNUSED")
object RedDiscordPresence : ModInitializer {
    val LOGGER = LoggerFactory.getLogger("red_discord_presence")
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
