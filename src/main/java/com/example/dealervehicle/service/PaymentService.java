package com.example.dealervehicle.service;

import com.example.dealervehicle.domain.Payment;
import com.example.dealervehicle.domain.PaymentStatus;
import com.example.dealervehicle.dto.PaymentInitiateRequest;
import com.example.dealervehicle.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final DealerService dealerService;

    public PaymentService(PaymentRepository paymentRepository, DealerService dealerService) {
        this.paymentRepository = paymentRepository;
        this.dealerService = dealerService;
    }

    public Payment initiate(PaymentInitiateRequest request) {
        dealerService.get(request.getDealerId());
        Payment payment = new Payment();
        payment.setDealerId(request.getDealerId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatus.PENDING);
        Payment saved = paymentRepository.save(payment);
        simulateCallbackAsync(saved.getId());
        return saved;
    }

    @Async
    protected CompletableFuture<Void> simulateCallbackAsync(Long paymentId) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                paymentRepository.findById(paymentId).ifPresent(p -> {
                    p.setStatus(PaymentStatus.SUCCESS);
                    paymentRepository.save(p);
                    log.info("Payment {} updated to SUCCESS", paymentId);
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Payment simulation interrupted", e);
            }
        });
    }
}




