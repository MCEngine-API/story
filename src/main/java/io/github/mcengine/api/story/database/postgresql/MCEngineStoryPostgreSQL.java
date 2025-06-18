package io.github.mcengine.api.story.database.postgresql;

import io.github.mcengine.api.story.database.IMCEngineStoryDB;

import org.bukkit.plugin.Plugin;
import java.sql.*;

/**
 * PostgreSQL implementation of the MCEngineStory database connection.
 */
public class MCEngineStoryPostgreSQL implements IMCEngineStoryDB {

    /** The Bukkit plugin instance. */
    private final Plugin plugin;

    /** Persistent PostgreSQL connection. */
    private final Connection conn;

    /**
     * Initializes the PostgreSQL connection using plugin configuration.
     *
     * @param plugin The Bukkit plugin instance.
     */
    public MCEngineStoryPostgreSQL(Plugin plugin) {
        this.plugin = plugin;

        String host = plugin.getConfig().getString("database.postgresql.host", "localhost");
        String port = plugin.getConfig().getString("database.postgresql.port", "5432");
        String dbName = plugin.getConfig().getString("database.postgresql.name", "mcengine_ai");
        String user = plugin.getConfig().getString("database.postgresql.user", "postgres");
        String pass = plugin.getConfig().getString("database.postgresql.password", "");

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        Connection tempConn = null;
        try {
            tempConn = DriverManager.getConnection(jdbcUrl, user, pass);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to connect to PostgreSQL: " + e.getMessage());
            e.printStackTrace();
        }
        this.conn = tempConn;
    }

    /**
     * Returns the current PostgreSQL database connection.
     *
     * @return Active {@link Connection} to the PostgreSQL database.
     */
    public Connection getDBConnection() {
        return conn;
    }

    /**
     * Disconnects the PostgreSQL connection.
     */
    public void disConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                plugin.getLogger().info("Disconnected from PostgreSQL.");
            }
        } catch (SQLException e) {
            plugin.getLogger().warning("Error closing PostgreSQL connection: " + e.getMessage());
        }
    }
}
