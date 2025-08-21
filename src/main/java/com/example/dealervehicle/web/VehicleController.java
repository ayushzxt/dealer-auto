package com.example.dealervehicle.web;

import com.example.dealervehicle.domain.Vehicle;
import com.example.dealervehicle.domain.Dealer;
import com.example.dealervehicle.dto.VehicleDto;
import com.example.dealervehicle.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@Valid @RequestBody VehicleDto dto) {
        Vehicle v = new Vehicle();
        Dealer d = new Dealer();
        d.setId(dto.getDealerId());
        v.setDealer(d);
        v.setModel(dto.getModel());
        v.setPrice(dto.getPrice());
        v.setStatus(dto.getStatus());
        return vehicleService.create(v);
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) { return vehicleService.get(id); }

    @GetMapping
    public List<Vehicle> list() { return vehicleService.list(); }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @Valid @RequestBody VehicleDto dto) {
        Vehicle v = new Vehicle();
        Dealer d = new Dealer();
        d.setId(dto.getDealerId());
        v.setDealer(d);
        v.setModel(dto.getModel());
        v.setPrice(dto.getPrice());
        v.setStatus(dto.getStatus());
        return vehicleService.update(id, v);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { vehicleService.delete(id); }

    @GetMapping("/premium")
    public List<Vehicle> listForPremiumDealers() { return vehicleService.listForPremiumDealers(); }
}


