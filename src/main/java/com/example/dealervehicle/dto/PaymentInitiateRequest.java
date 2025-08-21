package com.example.dealervehicle.dto;

import com.example.dealervehicle.domain.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PaymentInitiateRequest {
    @NotNull
    private Long dealerId;
    @NotNull
    @Min(1)
    private Double amount;
    @NotNull
    private PaymentMethod method;

    public Long getDealerId() { return dealerId; }
    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }
}




