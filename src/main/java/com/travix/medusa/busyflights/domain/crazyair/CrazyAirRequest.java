package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CrazyAirRequest {

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String origin;

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String destination;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String departureDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String returnDate;

    @Min(1)
    private int passengerCount;
}
