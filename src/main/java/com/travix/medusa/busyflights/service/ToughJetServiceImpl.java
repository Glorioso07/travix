package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.converter.ToughJetConverter;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetServiceImpl implements SupplierService {

    @Value("${toughjet.server.url}")
    private String TOUGHJET_SERVER_URL;
    @Value("${toughjet.controller}")
    private String TOUGHJET_CONTROLLER;
    @Value("${toughjet.resource}")
    private String TOUGHJET_RESOURCE;

    private static final String TOUGHJET_FROM_QUERYPARAM = "from";
    private static final String TOUGHJET_TO_QUERYPARAM = "to";
    private static final String TOUGHJET_OUTBOUND_DATE_QUERYPARAM = "outboundDate";
    private static final String TOUGHJET_INBOUND_DATE_QUERYPARAM = "inboundDate";
    private static final String TOUGHJET_NUMBER_OF_ADULTS_QUERYPARAM = "numberOfAdults";

    private RestTemplate restTemplate;

    @Autowired
    public ToughJetServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<SupplierResponse> getFlights(SupplierRequest request) {
        return getToughJetFlights(ToughJetConverter.convertToToughJetRequest(request)).stream().map(ToughJetConverter::convertToSupplierResponse).collect(Collectors.toList());
    }

    private List<ToughJetResponse> getToughJetFlights(ToughJetRequest toughJetRequest) {
        return Arrays.asList(restTemplate.getForObject(buildUri(toughJetRequest), ToughJetResponse[].class));
    }

    private String buildUri(ToughJetRequest toughJetRequest) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(TOUGHJET_SERVER_URL);
        uriBuilder.pathSegment(TOUGHJET_CONTROLLER);
        uriBuilder.pathSegment(TOUGHJET_RESOURCE);
        uriBuilder.queryParam(TOUGHJET_FROM_QUERYPARAM, toughJetRequest.getFrom());
        uriBuilder.queryParam(TOUGHJET_TO_QUERYPARAM, toughJetRequest.getTo());
        uriBuilder.queryParam(TOUGHJET_OUTBOUND_DATE_QUERYPARAM, toughJetRequest.getOutboundDate());
        uriBuilder.queryParam(TOUGHJET_INBOUND_DATE_QUERYPARAM, toughJetRequest.getInboundDate());
        uriBuilder.queryParam(TOUGHJET_NUMBER_OF_ADULTS_QUERYPARAM, toughJetRequest.getNumberOfAdults());
        return uriBuilder.toUriString();
    }
}
