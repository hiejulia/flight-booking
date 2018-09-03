package com.project.flightbooking;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class BookingNotificationController {
    /**
     * Booking notification
     * @param id
     * @return
     */
    @RequestMapping("/booking/notification")
    public String getBooking(@RequestParam(value="id", defaultValue="1") int id) {
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        BookingView bookingView = restTemplate.getForObject("http://booking-service/booking?id="+id, BookingView.class);
        String response = "NOTIFICATIONS";
        int number = 1;
        for(String notification : bookingView.getNotifications()){
            response += "<BR> Notification number "+(number++)+": "+notification;
        }
        return response;
    }

}