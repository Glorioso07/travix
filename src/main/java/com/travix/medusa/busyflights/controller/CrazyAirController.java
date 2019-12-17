package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${crazyair.controller}")
public class CrazyAirController {

    @GetMapping("${crazyair.resource}")
    public List<CrazyAirResponse> getFlights(@Valid CrazyAirRequest crazyAirRequest){
        return new ArrayList<>();
    }
}
