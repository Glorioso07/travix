package com.travix.medusa.busyflights.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrazyAirControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFlights_checkStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/crazyair/flights?origin=LON&destination=SEV&departureDate=2019-05-05&returnDate=2019-05-05&passengerCount=2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getFlights_checkStatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/crazyair/flights?origin=LONA&destination=SEV&departureDate=2019-05-05&returnDate=2019-05-05&passengerCount=1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}