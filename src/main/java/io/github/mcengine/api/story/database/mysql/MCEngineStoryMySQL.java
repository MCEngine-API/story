package io.github.mcengine.api.story.database.mysql;

import io.github.mcengine.story.api.database.IMCEngineStoryDB;
import org.bukkit.plugin.Plugin;
import java.sql.*;

/**
 * MySQL implementation of the MCEngineStory database connection.
 */
public class MCEngineStoryMySQL implements IMCEngineStoryDB {
    /** The Bukkit plugin instance. */
    private final Plugin plugin;

    /** Persistent MySQL connection. */
    private final Connection conn;

    /**
     * Initializes the MySQL connection using plugin configuration.
     *
     * @param plugin The Bukkit plugin instance.
     */
    public MCEngineStoryMySQL(Plugin plugin) {
        this.plugin = plugin;

        String host = plugin.getConfig().getString("database.mysql.host", "localhost");
        String port = plugin.getConfig().getString("database.mysql.port", "3306");
        String dbName = plugin.getConfig().getString("database.mysql.name", "mcengine_ai");
        String user = plugin.getConfig().getString("database.mysql.user", "root");
        String pass = plugin.getConfig().getString("database.mysql.password", "");

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&autoReconnect=true";

        Connection tempConn = null;
        try {
            tempConn = DriverManager.getConnection(jdbcUrl, user, pass);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to connect to MySQL: " + e.getMessage());
            e.printStackTrace();
        }
        this.conn = tempConn;
    }

    /**
     * Returns the current MySQL database connection.
     *
     * @return Active {@link Connection} to the MySQL database.
     */
    public Connection getDBConnection() {
        return conn;
    }

    /**
     * Disconnects the MySQL connection.
     */
    public void disConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                plugin.getLogger().info("Disconnected from MySQL.");
            }
        } catch (SQLException e) {
            plugin.getLogger().warning("Error closing MySQL connection: " + e.getMessage());
        }
    }
}
