
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pandora.clone.models.Song;
import redis.clients.jedis.Jedis;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

@Service
@Component
public class BookingRequestService implements InitializingBean {

    private Session session;
    private Jedis jedis;

    @Value("${neo4j.password}")
    private String neo4jPassword;

    @Value("${neo4j.username}")
    private String neo4jUsername;

    @Value("${neo4j.server}")
    private String neo4jServer;

    @Value("${redis.server}")
    private String redisServer;

    
    @Override
    public void afterPropertiesSet() throws Exception {
        Driver driver = GraphDatabase.driver(neo4jServer, AuthTokens.basic(neo4jUsername, neo4jPassword));
        this.session = driver.session();
        this.jedis = new Jedis(redisServer);
    }



    public List<BookingRequest> getTypes(){
        StatementResult result = this.session.run("match (b:BookingRequest) return distinct s.genre as genre");

        List<String> types = new ArrayList<>();
        while(result.hasNext()) {
            Record record = result.next();

            String type = record.get("type").asString();
            genres.add(genre);
        }
        return genres;
    }

    public Song playBookingRequestByType(String username, String genre) {
        // fetch first from cache redis 
        String songId = this.jedis.spop(username + "-type-" + type);

        if (songId == null) {
            this.populateByGenre(username, genre);
            songId = this.jedis.spop(username + "-type-" + genre);
        }

        int id = Integer.parseInt(songId);
        

        StatementResult result = this.session.run("match (b:BookingRequest) where ID(s)={id}" +
                "return s.filepath as filepath, b.flightNo as flightNo" 
                , parameters("id", id));

        if (result.hasNext()) {
            Record record = result.next();
            
            this.playSong(record.get("filepath").asString());
            return s;
        }
        return null;
    }


    // populate by type 
    public void populateByType(String type){
        this.jedis.del(username + "-type-" + type);
        StatementResult result = this.session.run("match (b:BookingRequest) where toUpper(b.type) = toUpper({type})" +
        "return ID(s) as id;", parameters("type", type));

result.list().stream().forEach(record -> {
    jedis.sadd(username + "-type-" + genre, Integer.toString(record.get("id").asInt()));
});

    }

    public boolean passengerConfirmBookingRequest(String passengerName, int id) {
        StatementResult result = this.session.run("match (u:User)-[confirm:CONFIRM]->(b:BookingRequest) where ID(s)={id} and u.username={username} return like;",
                parameters("id", id, "username", username));

        return result.hasNext();
    }



    public List<BookingRequest> getBookingRequestConfirmed(string passenger){
        // get from cache first 
        String bookingRequestId = this.jedis.spop(passenger+"-confirmed");
        // check if null -> call function and save to redis cache 

    }

  


    public void populateRandomBookingRequest(String passengerId){
        this.jedis.del(username + "-random");
        
                StatementResult result = this.session.run("MATCH (b:BookingRequest) " +
                        "RETURN ID(s) as id, s.filepath as filepath, b.flightNo as flightNo"
                        );
        
                result.list().stream().forEach(record -> jedis.sadd(username + "-random", Integer.toString(record.get("id").asInt())));
    }


    public confirmBookingRequest(Integer id,Integer userId){
        this.session.run("match (p:Passenger {username: {username}}), (b:BookingRequest) where ID(s)={id} create (u)-[:CONFIRMS]->(s);",
        parameters("id", id, "username", username));
    }

    public BookingRequest getBookingRequest(Integer id){
        StatementResult result = this.session.run("match (b:BookingRequest) where ID(s)={id}" +
        "return s.filepath as filepath;", parameters("id", id));

if (result.hasNext()) {
    Record record = result.next();
}
return null;
    }

    public void cancelBookingRequest(Integer id,String BookingRequestId){
        this.session.run("match (p:Passenger)-[bookingRequest:BOOKINGREQUESTS]->(b:BookingRequest) where ID(s)={id} and u.username={username} delete like;",
        parameters("id", id, "username", username));
    }



}
