package com.project.application;

import com.project.dao.UserDAO;
import com.project.health.Neo4jHealthCheck;
import com.project.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.neo4j.graphdb.GraphDatabaseService;
/**
 * Server application config
 */
public class ServerApplication extends Application<ServerConfiguration> {
    //java -jar target/neo4j-micro.jar server src/main/resources/config.yml

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {

        bootstrap.addBundle(new SwaggerBundle<ServerConfiguration>() {
            @Override
            public SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServerConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        final GraphDatabaseService graphDb = configuration.getDatabaseService();
        final Neo4jManager neo4jManager = new Neo4jManager(graphDb);

        final Neo4jHealthCheck neo4jHealthCheck = new Neo4jHealthCheck(graphDb);

        final UserDAO userDAO = new UserDAO(graphDb);

        final UserResource msgs = new UserResource(userDAO);
        environment.jersey().register(msgs);
        environment.lifecycle().manage(neo4jManager);
        environment.healthChecks().register("neo4jHealthCheck", neo4jHealthCheck);
    }
}