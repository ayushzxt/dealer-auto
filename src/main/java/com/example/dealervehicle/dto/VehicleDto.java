package com.example.dealervehicle.dto;

import com.example.dealervehicle.domain.VehicleStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VehicleDto {
    private Long id;

    @NotNull
    private Long dealerId;

    @NotBlank
    @Size(max = 120)
    private String model;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    private VehicleStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDealerId() { return dealerId; }
    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }
}


