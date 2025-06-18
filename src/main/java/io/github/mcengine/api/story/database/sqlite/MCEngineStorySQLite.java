package io.github.mcengine.api.story.database.sqlite;

import io.github.mcengine.story.api.database.IMCEngineStoryDB;

import org.bukkit.plugin.Plugin;
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

        String dbPath = plugin.getDataFolder().getAbsolutePath() + "/mcengine_ai.db";
        String jdbcUrl = "jdbc:sqlite:" + dbPath;

        Connection tempConn = null;
        try {
            tempConn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to connect to SQLite: " + e.getMessage());
            e.printStackTrace();
        }
        this.conn = tempConn;
    }

    /**
     * Returns the current SQLite database connection.
     *
     * @return Active {@link Connection} to the SQLite database.
     */
    @Override
    public Connection getDBConnection() {
        return conn;
    }

    /**
     * Disconnects the SQLite connection.
     */
    @Override
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
