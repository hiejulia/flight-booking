
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Order {
    private String orderId;

    private Date orderTimestamp = new Date();
    
}