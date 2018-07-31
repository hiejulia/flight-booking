package com.project.flightbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import java.io.File;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

/**
 * Server config - 
 * 
 */
public class ServerConfiguration extends Configuration {

    // dbpath
    @NotEmpty   
    private String dbpath;

    @JsonProperty("swagger")
    @Getter
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    // config Neo4J - Graph Database 
    private final GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
    
    private GraphDatabaseService databaseService = null;

    public GraphDatabaseService getDatabaseService() {       
        final File db = new File(dbpath);
        databaseService = graphDbFactory
                .newEmbeddedDatabaseBuilder(db)
                .setConfig(GraphDatabaseSettings.pagecache_memory, "1024M")
                .setConfig(GraphDatabaseSettings.string_block_size, "240")
                .setConfig(GraphDatabaseSettings.array_block_size, "2400")
                .setConfig(GraphDatabaseSettings.batch_inserter_batch_size, "2054")
                .newGraphDatabase();
        return databaseService;
    }

    @JsonProperty("dbpath")
    public String getDbpath() {
        return dbpath;
    }   

}