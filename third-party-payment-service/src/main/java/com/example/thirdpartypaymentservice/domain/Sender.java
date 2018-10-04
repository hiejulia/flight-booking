
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


// Sender
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Sender {

    private String userName;

    
    private String creditCardNumber;

    private String expirationDate;
    
    private String securityCode;
}