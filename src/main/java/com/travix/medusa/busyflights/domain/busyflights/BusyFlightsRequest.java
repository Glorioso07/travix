package com.travix.medusa.busyflights.domain.busyflights;


import com.travix.medusa.busyflights.domain.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class BusyFlightsRequest {

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String origin;

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String destination;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;

    @Min(1)
    @Max(4)
    private int numberOfPassengers;
}
