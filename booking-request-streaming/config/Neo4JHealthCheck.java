
import com.codahale.metrics.health.HealthCheck;
import org.neo4j.graphdb.GraphDatabaseService;

public class Neo4jHealthCheck extends HealthCheck {

    
    private final GraphDatabaseService graphDb;

    public Neo4jHealthCheck(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}