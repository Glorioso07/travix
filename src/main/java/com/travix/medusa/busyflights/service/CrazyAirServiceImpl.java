package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.converter.CrazyAirConverter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirServiceImpl implements SupplierService {

    @Value("${crazyair.server.url}")
    private String CRAZYAIR_SERVER_URL;
    @Value("${crazyair.controller}")
    private String CRAZYAIR_CONTROLLER;
    @Value("${crazyair.resource}")
    private String CRAZYAIR_RESOURCE;

    private static final String CRAZYAIR_ORIGIN_QUERYPARAM = "origin";
    private static final String CRAZYAIR_DESTINATION_QUERYPARAM = "destination";
    private static final String CRAZYAIR_DEPARTURE_DATE_QUERYPARAM = "departureDate";
    private static final String CRAZYAIR_RETURN_DATE_QUERYPARAM = "returnDate";
    private static final String CRAZYAIR_PASSENGER_COUNT_QUERYPARAM = "passengerCount";

    private RestTemplate restTemplate;

    @Autowired
    public CrazyAirServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<SupplierResponse> getFlights(SupplierRequest request) {
        return getCrazyAirFlights(CrazyAirConverter.convertToCrazyAirRequest(request)).stream().map(CrazyAirConverter::convertToSupplierResponse).collect(Collectors.toList());
    }

    public List<CrazyAirResponse> getCrazyAirFlights(CrazyAirRequest crazyAirRequest) {
        return Arrays.asList(restTemplate.getForObject(buildUri(crazyAirRequest), CrazyAirResponse[].class));
    }

    private String buildUri(CrazyAirRequest crazyAirRequest) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(CRAZYAIR_SERVER_URL);
        uriBuilder.pathSegment(CRAZYAIR_CONTROLLER);
        uriBuilder.pathSegment(CRAZYAIR_RESOURCE);
        uriBuilder.queryParam(CRAZYAIR_ORIGIN_QUERYPARAM, crazyAirRequest.getOrigin());
        uriBuilder.queryParam(CRAZYAIR_DESTINATION_QUERYPARAM, crazyAirRequest.getDestination());
        uriBuilder.queryParam(CRAZYAIR_DEPARTURE_DATE_QUERYPARAM, crazyAirRequest.getDepartureDate());
        uriBuilder.queryParam(CRAZYAIR_RETURN_DATE_QUERYPARAM, crazyAirRequest.getReturnDate());
        uriBuilder.queryParam(CRAZYAIR_PASSENGER_COUNT_QUERYPARAM, crazyAirRequest.getPassengerCount());
        return uriBuilder.toUriString();
    }
}
