package io.github.mcengine.api.story.dlc;

import org.bukkit.plugin.Plugin;

/**
 * Interface for AI DLC modules that can be dynamically loaded.
 */
public interface IMCEngineStoryDLC {

    /**
     * Called when the DLC is loaded by the AI engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);
}
