package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.SupplierEnum;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.time.format.DateTimeFormatter;

public class ToughJetConverter {

    public static ToughJetRequest convertToToughJetRequest(SupplierRequest supplierRequest) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(supplierRequest.getOrigin());
        toughJetRequest.setTo(supplierRequest.getDestination());
        toughJetRequest.setOutboundDate(supplierRequest.getDepartureDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        toughJetRequest.setInboundDate(supplierRequest.getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        toughJetRequest.setNumberOfAdults(supplierRequest.getNumberOfPassengers());
        return toughJetRequest;
    }

    public static SupplierResponse convertToSupplierResponse(ToughJetResponse toughJetResponse) {
        SupplierResponse response = new SupplierResponse();
        response.setAirline(toughJetResponse.getCarrier());
        response.setSupplier(SupplierEnum.ToughJet.name());
        response.setFare(toughJetResponse.getFare());
        response.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        response.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        response.setDepartureDate(toughJetResponse.getOutboundDateTime());
        response.setArrivalDate(toughJetResponse.getInboundDateTime());
        return response;
    }
}
