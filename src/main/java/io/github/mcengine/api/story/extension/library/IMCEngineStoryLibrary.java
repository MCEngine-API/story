package io.github.mcengine.api.story.extension.library;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Story-based Library module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to provide shared services and utilities for story features.
 */
public interface IMCEngineStoryLibrary {

    /**
     * Called when the Story Library is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Story Library is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Story Library instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
