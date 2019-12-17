package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusyFlightsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusyFlightServiceImpl busyFlightService;

    private List<BusyFlightsResponse> busyFlightsResponse;

    @Before
    public void before(){
        buildBusyFlightsResponses();
        Mockito.when(busyFlightService.getFlightsOrderedByFare(any(BusyFlightsRequest.class))).thenReturn(busyFlightsResponse);
    }

    private void buildBusyFlightsResponses() {
        busyFlightsResponse = new ArrayList<>();
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline("Airline");
        response.setSupplier("ToughJet");
        response.setFare("25.21");
        response.setDestinationAirportCode("LON");
        response.setDestinationAirportCode("SEV");
        response.setDepartureDate("2019-05-05");
        response.setArrivalDate("2019-05-06");
        busyFlightsResponse.add(response);
    }

    @Test
    public void getFlightsOrderedByFare_checkStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/busyflights/flights?origin=LON&destination=SEV&departureDate=2019-05-05&returnDate=2019-05-05&numberOfPassengers=1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getFlightsOrderedByFare_checkStatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/busyflights/flights?origin=LONA&destination=SEV&departureDate=2019-05-05&returnDate=2019-05-05&numberOfPassengers=1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}