package com.example.dealervehicle.web;

import com.example.dealervehicle.domain.Payment;
import com.example.dealervehicle.dto.PaymentInitiateRequest;
import com.example.dealervehicle.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Payment initiate(@Valid @RequestBody PaymentInitiateRequest request) {
        return paymentService.initiate(request);
    }
}






