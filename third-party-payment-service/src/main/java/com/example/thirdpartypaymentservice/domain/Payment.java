import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Payment {
    private Long paymentId;

    private Sender sender;

    // private Recipient recipient;
    private Order order;

    private Double totalAmount;
    private Date timestamp = new Date();
    private String paymentStatus;

}