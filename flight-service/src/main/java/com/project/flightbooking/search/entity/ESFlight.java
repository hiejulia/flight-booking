import org.springframework.data.annotation.Id; 
import org.springframework.data.elasticsearch.annotations.Document; 

@Document(indexName = "flight", type = "external" ) 
public class Flight { 

  @Id 
  private String id ; 

  private String flightNo ;    

  private String departure;

  private String arrival;