package com.example.dealervehicle.web;

import com.example.dealervehicle.domain.SubscriptionType;
import com.example.dealervehicle.dto.DealerDto;
import com.example.dealervehicle.dto.VehicleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DealerVehicleIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void dealerAndVehicleCrud() throws Exception {
        String tokenResponse = mockMvc.perform(post("/api/auth/token").param("username", "demo"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String token = objectMapper.readTree(tokenResponse).get("token").asText();

        DealerDto dealer = new DealerDto();
        dealer.setName("Dealer A");
        dealer.setEmail("dealerA@example.com");
        dealer.setSubscriptionType(SubscriptionType.PREMIUM);

        String dealerJson = objectMapper.writeValueAsString(dealer);

        String dealerResponse = mockMvc.perform(post("/api/dealers")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(dealerJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long dealerId = objectMapper.readTree(dealerResponse).get("id").asLong();

        VehicleDto vehicle = new VehicleDto();
        vehicle.setDealerId(dealerId);
        vehicle.setModel("Model X");
        vehicle.setPrice(100000.0);
        vehicle.setStatus(com.example.dealervehicle.domain.VehicleStatus.AVAILABLE);

        mockMvc.perform(post("/api/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/vehicles/premium").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}


