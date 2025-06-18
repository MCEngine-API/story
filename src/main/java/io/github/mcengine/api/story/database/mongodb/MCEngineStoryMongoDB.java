package io.github.mcengine.api.story.database.mongodb;

import io.github.mcengine.story.api.database.IMCEngineStoryDB;

import com.mongodb.client.*;
import org.bson.Document;
import org.bukkit.plugin.Plugin;
import java.sql.Connection;

/**
 * MongoDB implementation of the MCEngineStory database connection.
 * <p>
 * Note: MongoDB does not use JDBC, so {@link #getDBConnection()} always returns {@code null}.
 */
public class MCEngineStoryMongoDB implements IMCEngineStoryDB {

    /** The Bukkit plugin instance. */
    private final Plugin plugin;

    /** MongoDB client instance. */
    private final MongoClient mongoClient;

    /** MongoDB database reference. */
    private final MongoDatabase database;

    /** MongoDB collection used for token storage. */
    private final MongoCollection<Document> collection;

    /**
     * Initializes the MongoDB connection using configuration from the plugin.
     *
     * @param plugin The Bukkit plugin instance.
     */
    public MCEngineStoryMongoDB(Plugin plugin){
        this.plugin = plugin;

        String uri = plugin.getConfig().getString("database.mongodb.uri", "mongodb://localhost:27017");
        String dbName = plugin.getConfig().getString("database.mongodb.name", "mcengine_ai");
        String collectionName = plugin.getConfig().getString("database.mongodb.collection", "artificialintelligence");

        this.mongoClient = MongoClients.create(uri);
        this.database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);

        plugin.getLogger().info("Connected to MongoDB: " + uri + "/" + dbName + "." + collectionName);
    }

    /**
     * MongoDB does not support JDBC connections. This method always returns {@code null}.
     *
     * @return {@code null}, since MongoDB is not compatible with {@link Connection}.
     */
    public Connection getDBConnection() {
        return null;
    }

    /**
     * Disconnects the MongoDB client.
     */
    public void disConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            plugin.getLogger().info("Disconnected from MongoDB.");
        }
    }
}
