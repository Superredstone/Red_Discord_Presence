package com.superredstone.red_discord_presence.events

import com.superredstone.red_discord_presence.RedDiscordPresence.richPresenceHandler
import com.superredstone.red_discord_presence.config.ConfigHandler
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler

class PlayerJoinHandler : ClientPlayConnectionEvents.Join {
    override fun onPlayReady(handler: ClientPlayNetworkHandler?, sender: PacketSender?, client: MinecraftClient?) {
        val cfg = ConfigHandler.readConfig()

        if (MinecraftClient.getInstance().isInSingleplayer) {
            richPresenceHandler.setStatus("Single player", "", cfg.singleplayerImage ?: "", cfg.imageText ?: "")
        } else {
             richPresenceHandler.setStatus("Multiplayer", "", cfg.multiplayerImage ?: "", cfg.imageText ?: "")
        }
    }
}
