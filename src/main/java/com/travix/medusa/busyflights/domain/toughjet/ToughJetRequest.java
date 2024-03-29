package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ToughJetRequest {

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String from;

    @NotNull
    @Pattern(regexp = DomainConstants.IATA_CODE_EXPREG)
    private String to;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String outboundDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String inboundDate;

    @Min(1)
    private int numberOfAdults;
}
