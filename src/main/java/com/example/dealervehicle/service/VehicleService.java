package com.example.dealervehicle.service;

import com.example.dealervehicle.domain.Vehicle;
import com.example.dealervehicle.domain.Dealer;
import com.example.dealervehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final DealerService dealerService;

    public VehicleService(VehicleRepository vehicleRepository, DealerService dealerService) {
        this.vehicleRepository = vehicleRepository;
        this.dealerService = dealerService;
    }

    public Vehicle create(Vehicle vehicle) {
        Dealer dealer = dealerService.get(vehicle.getDealer().getId());
        vehicle.setDealer(dealer);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle get(Long id) { return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found")); }
    public List<Vehicle> list() { return vehicleRepository.findAll(); }

    public Vehicle update(Long id, Vehicle updated) {
        Vehicle v = get(id);
        if (updated.getDealer() != null) {
            Dealer dealer = dealerService.get(updated.getDealer().getId());
            v.setDealer(dealer);
        }
        v.setModel(updated.getModel());
        v.setPrice(updated.getPrice());
        v.setStatus(updated.getStatus());
        return vehicleRepository.save(v);
    }

    public void delete(Long id) { vehicleRepository.deleteById(id); }

    public List<Vehicle> listForPremiumDealers() {
        return vehicleRepository.findVehiclesForPremiumDealers();
    }
}


