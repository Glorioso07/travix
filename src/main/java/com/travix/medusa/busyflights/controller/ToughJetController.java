package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${toughjet.controller}")
public class ToughJetController {

    @GetMapping("${toughjet.resource}")
    public List<ToughJetResponse> getFlights(@Valid ToughJetRequest toughJetRequest){
        return new ArrayList<>();
    }
}
