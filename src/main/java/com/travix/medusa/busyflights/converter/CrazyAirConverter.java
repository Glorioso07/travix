package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.SupplierEnum;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class CrazyAirConverter {

    public static CrazyAirRequest convertToCrazyAirRequest(SupplierRequest supplierRequest) {
        CrazyAirRequest request = new CrazyAirRequest();
        request.setOrigin(supplierRequest.getOrigin());
        request.setDestination(supplierRequest.getDestination());
        request.setDepartureDate(supplierRequest.getDepartureDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setReturnDate(supplierRequest.getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setPassengerCount(supplierRequest.getNumberOfPassengers());
        return request;
    }

    public static SupplierResponse convertToSupplierResponse(CrazyAirResponse crazyAirResponse) {
        SupplierResponse response = new SupplierResponse();
        response.setAirline(crazyAirResponse.getAirline());
        response.setSupplier(SupplierEnum.CrazyAir.name());
        response.setFare(BigDecimal.valueOf(crazyAirResponse.getPrice()).setScale(2, RoundingMode.HALF_UP).toString());
        response.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        response.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        response.setDepartureDate(crazyAirResponse.getDepartureDate());
        response.setArrivalDate(crazyAirResponse.getArrivalDate());
        return response;
    }
}
