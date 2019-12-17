package com.travix.medusa.busyflights.domain.toughjet;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class ToughJetResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;

    public String getFare() {
        final BigDecimal basePriceDiscounted = this.getBasePriceDiscounted();
        return basePriceDiscounted.add(basePriceDiscounted.multiply(BigDecimal.valueOf(this.getTax()))).setScale(2, RoundingMode.HALF_UP).toString();
    }

    private BigDecimal getBasePriceDiscounted () {
        final BigDecimal basePrice = BigDecimal.valueOf(this.getBasePrice());
        return basePrice.subtract(basePrice.multiply(BigDecimal.valueOf(this.getDiscount()).divide(BigDecimal.valueOf(100))));
    }
}
