package com.travix.medusa.busyflights.domain.crazyair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrazyAirResponse {
    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
