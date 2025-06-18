package io.github.mcengine.api.story.database.mongodb;

import io.github.mcengine.story.api.database.IMCEngineStoryDB;

import com.mongodb.client.*;
import org.bson.Document;
import org.bukkit.plugin.Plugin;
import java.sql.Connection;

/**
 * MongoDB implementation of the MCEngineStory database connection.
 * Since MongoDB doesn't use {@link java.sql.Connection}, getDBConnection returns null.
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
     * MongoDB does not use JDBC, so this returns null.
     *
     * @return Always returns null.
     */
    @Override
    public Connection getDBConnection() {
        return null;
    }
}
