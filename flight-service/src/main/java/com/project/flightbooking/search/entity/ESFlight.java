import org.springframework.data.annotation.Id; 
import org.springframework.data.elasticsearch.annotations.Document; 

@Document(indexName = "flight", type = "flights" ) 
@Data 
public class Flight { 

  @Id 
  private String id ; 

  @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
  private String flightNo ;   
  
  @Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "smartcn")
  private String flightName;

  private String departure;


  private String arrival;

  private Date departureTime;


  private Date arrivalTime


  private Date createdAt;


  private Date updatedAt;