package io.github.mcengine.api.story;

import org.bukkit.plugin.Plugin;

public class MCEngineStoryApi {

    private static MCEngineStoryApi instance;
    private Plugin plugin;

    /**
     * Constructs the currency API instance and initializes the appropriate database connection.
     *
     * @param plugin  The Bukkit plugin instance.
     */
    public MCEngineStoryApi(Plugin plugin) {
        instance = this;
        this.plugin = plugin;
    }

    /**
     * Gets the global API singleton instance.
     *
     * @return The {@link MCEngineStoryApi} instance.
     */
    public static MCEngineStoryApi getApi() {
        return instance;
    }

    /**
     * Gets the Bukkit plugin instance linked to this API.
     *
     * @return The plugin instance.
     */
    public Plugin getPlugin() {
        return plugin;
    }
}
