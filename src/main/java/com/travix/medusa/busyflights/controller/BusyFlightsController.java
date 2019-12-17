package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${busyflights.controller}")
public class BusyFlightsController {

    private BusyFlightServiceImpl service;

    @Autowired
    public BusyFlightsController(BusyFlightServiceImpl service){
        this.service = service;
    }

    @GetMapping("${busyflights.resource}")
    public List<BusyFlightsResponse> getFlightsOrderedByFare(@Valid BusyFlightsRequest request){
        return service.getFlightsOrderedByFare(request);
    }
}