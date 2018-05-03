package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(path = "paymentBookings")
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Find payment by sender user name
    Payment findTopBySenderUserName(@Param("userName") String userName);

    // Update payment status
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Payment p set p.paymentStatus = ?1 where p.paymentId = ?2")
    void updatePaymentStatusById(String paymentStatus, Long paymentId);

    // http://localhost:9001/paymentBookings/1
    // Get one payment by id
    @RestResource(path = "paymentId")
    Payment findByPaymentId(@Param("pxaymentId") Long paymentId);
}