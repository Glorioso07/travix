package com.travix.medusa.busyflights.domain.supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponse {
    private String airline;
    private String supplier;
    private String fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
