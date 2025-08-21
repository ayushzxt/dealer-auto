package com.example.dealervehicle.repository;

import com.example.dealervehicle.domain.Dealer;
import com.example.dealervehicle.domain.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    List<Dealer> findBySubscriptionType(SubscriptionType type);
}




