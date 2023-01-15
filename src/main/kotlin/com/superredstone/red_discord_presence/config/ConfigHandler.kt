package com.superredstone.red_discord_presence.config

import com.google.gson.Gson
import net.fabricmc.loader.api.FabricLoader
import java.io.File

object ConfigHandler {
    private const val defaultClientId = 1063447620556685342

    fun readConfig(): Config {
        val path = FabricLoader.getInstance().configDir.toString() + "/red_discord_presence.json"
        val file = File(path)

        if (!file.exists()) {
            file.createNewFile()

            val newConfig = Config(true, defaultClientId, "icon", "icon", "icon", "Minecraft")

            file.writeText(Gson().toJson(newConfig))
        }

        val txt = file.readText()

        return Gson().fromJson(txt, Config::class.java)
    }

    fun validateConfig(): Long {
        return readConfig().clientId ?: defaultClientId
    }
}

class Config(var enableRichPresence: Boolean?, var clientId: Long?, var menuImage: String?, var singleplayerImage: String?, var multiplayerImage: String?, var imageText: String?) {}