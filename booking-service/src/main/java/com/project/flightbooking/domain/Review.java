
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.demo.domain.util.CustomDateTimeDeserializer;
import com.springboot.demo.domain.util.CustomDateTimeSerializer;
import com.springboot.demo.web.rest.dto.ReviewDetailsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Review extends BaseEntity {
    
    @ManyToOne(optional = false)
    private Hotel hotel;

    @Column(nullable = false, name = "idx")
    private int index;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)// JsonSerialize - using CustomDateTimeSerialize.class 
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime checkInDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Audited
    private TripType tripType;

    @Column(nullable = false)
    @Audited
    private String title;

    @Column(nullable = false, length = 5000)
    @Audited
    private String details;

    protected Review() {
    }

    public Review(Hotel hotel, int index, ReviewDetailsDto details) {
        Assert.notNull(hotel, "Hotel must not be null");
        Assert.notNull(details, "Details must not be null");
        this.hotel = hotel;
        this.index = index;
        this.rating = details.getRating();
        this.checkInDate = details.getCheckInDate();
        this.tripType = details.getTripType();
        this.title = details.getTitle();
        this.details = details.getDetails();
    }

}