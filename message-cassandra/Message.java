
import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Table
public class Message {
    
    private Date timestamp;
    private String message;



    @PrimaryKey
    @CassandraType(type= DataType.Name.UUID)
    private UUID id;

    protected Message() {}

    protected Message(com.computerbooth.test.Message message) {
        this.id = message.getId();
        this.timestamp = new Date(message.getTimestamp().toEpochMilli());
        this.message = message.getMessage();
    }

    public Message(Instant timestamp, String message) {
        this.id = UUID.randomUUID();
        this.timestamp = new Date(timestamp.toEpochMilli());
        this.message = message;
    }

    public Instant getTimestamp() {
        return Instant.ofEpochMilli(timestamp.getTime());
    }

    public String getMessage() {
        return message;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Timestamp: " + getTimestamp() + ", ID: " + getId() + ", message: \"" + getMessage() + "\"";
    }
}