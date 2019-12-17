package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;

public class BusyFlightsConverter {

    public static BusyFlightsResponse convertToBusyFlightsResponse(SupplierResponse supplierResponse) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline(supplierResponse.getAirline());
        response.setSupplier(supplierResponse.getSupplier());
        response.setFare(supplierResponse.getFare());
        response.setDepartureAirportCode(supplierResponse.getDepartureAirportCode());
        response.setDestinationAirportCode(supplierResponse.getDestinationAirportCode());
        response.setDepartureDate(supplierResponse.getDepartureDate());
        response.setArrivalDate(supplierResponse.getArrivalDate());
        return response;
    }

    public static SupplierRequest convertToSupplierRequest(BusyFlightsRequest busyFlightsRequest) {
        SupplierRequest request = new SupplierRequest();
        request.setOrigin(busyFlightsRequest.getOrigin());
        request.setDestination(busyFlightsRequest.getDestination());
        request.setDepartureDate(busyFlightsRequest.getDepartureDate());
        request.setReturnDate(busyFlightsRequest.getReturnDate());
        request.setNumberOfPassengers(busyFlightsRequest.getNumberOfPassengers());
        return request;
    }

}
