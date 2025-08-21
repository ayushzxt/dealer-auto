package com.example.dealervehicle.web;

import com.example.dealervehicle.domain.Dealer;
import com.example.dealervehicle.dto.DealerDto;
import com.example.dealervehicle.service.DealerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dealer create(@Valid @RequestBody DealerDto dto) {
        Dealer d = new Dealer();
        d.setName(dto.getName());
        d.setEmail(dto.getEmail());
        d.setSubscriptionType(dto.getSubscriptionType());
        return dealerService.create(d);
    }

    @GetMapping
    public List<Dealer> list() {
        return dealerService.list();
    }

    @GetMapping("/{id}")
    public Dealer get(@PathVariable Long id) {
        return dealerService.get(id);
    }

    @PutMapping("/{id}")
    public Dealer update(@PathVariable Long id, @Valid @RequestBody DealerDto dto) {
        Dealer d = new Dealer();
        d.setName(dto.getName());
        d.setEmail(dto.getEmail());
        d.setSubscriptionType(dto.getSubscriptionType());
        return dealerService.update(id, d);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dealerService.delete(id);
    }
}


