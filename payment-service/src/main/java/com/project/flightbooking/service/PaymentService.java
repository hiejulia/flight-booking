package com.project.flightbooking.service;



import com.project.flightbooking.domain.Payment;
import com.project.flightbooking.domain.Sender;
import com.project.flightbooking.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Find sender by sender user name
     * @param userName
     * @return
     */
    public Sender findBySenderUsername(String userName) {

        Sender sender = new Sender();

        Payment payment = this.paymentRepository.findTopBySenderUserName(userName);
        if (payment != null) {
            sender = payment.getSender();
            sender.setSecurityCode("");
        }

        return sender;
    }

    /**
     * save payment booking to database
     * @param payment
     * @return
     */
    public Payment savePaymentBooking(Payment payment) {
        return paymentRepository.save(payment);
    }


    /**
     * Update payment status
     * @param paymentStatus
     * @param paymentId
     * @return
     */
    public Payment updatePaymentStatusById(String paymentStatus, Long paymentId) {
        paymentRepository.updatePaymentStatusById(paymentStatus, paymentId);
        return paymentRepository.findByPaymentId(paymentId);
    }

}