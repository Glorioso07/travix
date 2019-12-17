package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.converter.BusyFlightsConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightServiceImpl implements BusyFlightService {

    private List<SupplierService> supplierServices;

    @Autowired
    public BusyFlightServiceImpl(List<SupplierService> suppliers){
        this.supplierServices = suppliers;
    }

    public List<BusyFlightsResponse> getFlightsOrderedByFare(BusyFlightsRequest request) {
        return supplierServices.stream()
                .map(supplierService -> supplierService.getFlights(BusyFlightsConverter.convertToSupplierRequest(request)))
                .flatMap(List::stream)
                .map(BusyFlightsConverter::convertToBusyFlightsResponse)
                .sorted(Comparator.comparing(b -> Double.valueOf(b.getFare())))
                .collect(Collectors.toList());
    }
}
