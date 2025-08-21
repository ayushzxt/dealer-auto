package com.example.dealervehicle.service;

import com.example.dealervehicle.domain.Dealer;
import com.example.dealervehicle.domain.SubscriptionType;
import com.example.dealervehicle.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public Dealer create(Dealer dealer) { return dealerRepository.save(dealer); }
    public Dealer get(Long id) { return dealerRepository.findById(id).orElseThrow(() -> new RuntimeException("Dealer not found")); }
    public List<Dealer> list() { return dealerRepository.findAll(); }
    public Dealer update(Long id, Dealer updated) {
        Dealer d = get(id);
        d.setName(updated.getName());
        d.setEmail(updated.getEmail());
        d.setSubscriptionType(updated.getSubscriptionType());
        return dealerRepository.save(d);
    }
    public void delete(Long id) { dealerRepository.deleteById(id); }
    public List<Dealer> findPremiumDealers() { return dealerRepository.findBySubscriptionType(SubscriptionType.PREMIUM); }
}




