
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pandora.clone.authorization.JwtTokenUtil;
import pandora.clone.models.Song;
import pandora.clone.services.MusicServices;
import pandora.clone.services.UserServices;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class BookingRequestController {

    @Autowired
    private BookingRequestService BookingRequestService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServices userServices;

    @CrossOrigin
    @GetMapping("/stream/bookingRequest/{id}")
    public void streamBookingRequest(@PathVariable Integer id, HttpServletResponse response) {
        byte[] bytes = BookingRequestService.getType(id);

        try {
            ServletOutputStream sos = response.getOutputStream();
            sos.write(bytes, 0, bytes.length);
            sos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    


}
