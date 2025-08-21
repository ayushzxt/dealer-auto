package com.example.dealervehicle.repository;

import com.example.dealervehicle.domain.Vehicle;
import com.example.dealervehicle.domain.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDealer_Id(Long dealerId);
    List<Vehicle> findByStatus(VehicleStatus status);

    @Query("select v from Vehicle v where v.dealer.subscriptionType = 'PREMIUM'")
    List<Vehicle> findVehiclesForPremiumDealers();

    @Query("select v from Vehicle v where v.dealer.id in :dealerIds")
    List<Vehicle> findByDealerIds(@Param("dealerIds") List<Long> dealerIds);
}


