package io.github.mcengine.api.story.extension.api;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Story-based API module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to expose story-related APIs to the engine and other modules.
 */
public interface IMCEngineStoryAPI {

    /**
     * Called when the Story API is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Story API is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Story API instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
