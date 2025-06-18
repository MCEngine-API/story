package io.github.mcengine.api.story;

import io.github.mcengine.api.story.database.IMCEngineStoryDB;
import io.github.mcengine.api.story.database.mongodb.MCEngineStoryMongoDB;
import io.github.mcengine.api.story.database.mysql.MCEngineStoryMySQL;
import io.github.mcengine.api.story.database.postgresql.MCEngineStoryPostgreSQL;
import io.github.mcengine.api.story.database.sqlite.MCEngineStorySQLite;

import java.sql.Connection;

import org.bukkit.plugin.Plugin;

public class MCEngineStoryApi {

    /**
     * Singleton instance of the API.
     */
    private static MCEngineStoryApi instance;

    /**
     * Database handler instance for storing and retrieving player tokens.
     */
    private final IMCEngineStoryDB db;

    /**
     * The Bukkit plugin instance associated with this AI API.
     */
    private Plugin plugin;

    /**
     * Constructs the currency API instance and initializes the appropriate database connection.
     *
     * @param plugin  The Bukkit plugin instance.
     */
    public MCEngineStoryApi(Plugin plugin) {
        instance = this;
        this.plugin = plugin;
        
        // Initialize database based on type
        String dbType = plugin.getConfig().getString("database.type", "sqlite").toLowerCase();
        switch (dbType) {
            case "sqlite" -> this.db = new MCEngineStorySQLite(plugin);
            case "mysql" -> this.db = new MCEngineStoryMySQL(plugin);
            case "postgresql" -> this.db = new MCEngineStoryPostgreSQL(plugin);
            case "mongodb" -> this.db = new MCEngineStoryMongoDB(plugin);
            default -> throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
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

    /**
     * Returns the database handler implementation.
     *
     * @return The database API implementation.
     */
    public IMCEngineStoryDB getDB() {
        return db;
    }

    /**
     * Retrieves the active database connection used by the AI plugin.
     * <p>
     * This delegates to the underlying database implementation such as SQLite.
     * Useful for executing custom SQL queries or diagnostics externally.
     *
     * @return The {@link Connection} instance for the configured database.
     */
    public Connection getDBConnection() {
        return db.getDBConnection();
    }
}
