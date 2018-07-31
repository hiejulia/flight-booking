package com.project.flightbooking;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Passenger {
    
    public static Passenger fromNode(Node node) {
        try (Transaction t = node.getGraphDatabase().beginTx()) {
            User e = new User(
                     node.getId(),
                    (String) node.getProperty("firstname"),
                    (String) node.getProperty("lastname"),
                    (String) node.getProperty("email")
                    
            );
            t.success();
            return e;
        }
    }

    private long id;
    private String firstname;
    private String lastname;
    private String email;
   

}