package io.github.mcengine.api.story.extension.agent;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Story-based Agent module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate story-oriented agents into the system.
 */
public interface IMCEngineStoryAgent {

    /**
     * Called when the Story Agent is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Story Agent is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Story Agent instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
