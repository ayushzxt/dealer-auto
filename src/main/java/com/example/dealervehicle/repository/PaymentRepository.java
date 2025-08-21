package com.example.dealervehicle.repository;

import com.example.dealervehicle.domain.Payment;
import com.example.dealervehicle.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDealerId(Long dealerId);
    List<Payment> findByStatus(PaymentStatus status);
}




