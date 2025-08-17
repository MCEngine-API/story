package io.github.mcengine.api.story.extension.dlc;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Story-based DLC module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate downloadable content focused on story mechanics.
 */
public interface IMCEngineStoryDLC {

    /**
     * Called when the Story DLC is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Story DLC is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Story DLC instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
