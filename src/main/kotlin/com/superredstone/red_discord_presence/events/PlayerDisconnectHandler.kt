package com.superredstone.red_discord_presence.events

import com.superredstone.red_discord_presence.RedDiscordPresence
import com.superredstone.red_discord_presence.config.ConfigHandler
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler

class PlayerDisconnectHandler : ClientPlayConnectionEvents.Disconnect {
    override fun onPlayDisconnect(handler: ClientPlayNetworkHandler?, client: MinecraftClient?) {
        val cfg = ConfigHandler.readConfig()
        RedDiscordPresence.richPresenceHandler.setStatus("Main menu", "", cfg.menuImage ?: "", cfg.imageText ?: "", RedDiscordPresence.richPresenceHandler.startTime)
    }
}