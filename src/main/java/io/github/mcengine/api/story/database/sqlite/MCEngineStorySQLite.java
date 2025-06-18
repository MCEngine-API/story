package io.github.mcengine.api.story.database.sqlite;

import io.github.mcengine.api.story.database.IMCEngineStoryDB;

import org.bukkit.plugin.Plugin;
import java.io.File;
import java.sql.*;

/**
 * SQLite implementation of the MCEngineStory database connection.
 */
public class MCEngineStorySQLite implements IMCEngineStoryDB {

    /** The Bukkit plugin instance. */
    private final Plugin plugin;

    /** Persistent SQLite connection. */
    private final Connection conn;

    /**
     * Initializes the SQLite connection using plugin configuration.
     *
     * @param plugin The Bukkit plugin instance.
     */
    public MCEngineStorySQLite(Plugin plugin) {
        this.plugin = plugin;
        String fileName = plugin.getConfig().getString("database.sqlite.path", "story.db");
        File dbFile = new File(plugin.getDataFolder(), fileName);

        // Create the file if it doesn't exist
        if (!dbFile.exists()) {
            try {
                boolean created = dbFile.createNewFile();
                if (created) {
                    plugin.getLogger().info("SQLite database file created: " + dbFile.getAbsolutePath());
                }
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to create SQLite database file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        String databaseUrl = "jdbc:sqlite:" + dbFile.getAbsolutePath();

        Connection tempConn = null;
        try {
            tempConn = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to open SQLite connection: " + e.getMessage());
            e.printStackTrace();
        }
        this.conn = tempConn;
    }

    /**
     * Returns the current SQLite database connection.
     *
     * @return Active {@link Connection} to the SQLite database.
     */
    public Connection getDBConnection() {
        return conn;
    }

    /**
     * Disconnects the SQLite connection.
     */
    public void disConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                plugin.getLogger().info("Disconnected from SQLite.");
            }
        } catch (SQLException e) {
            plugin.getLogger().warning("Error closing SQLite connection: " + e.getMessage());
        }
    }
}
