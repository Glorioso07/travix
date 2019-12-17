package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CrazyAirConverterTest {

    private SupplierRequest supplierRequest;
    private CrazyAirResponse crazyAirResponse;

    @Before
    public void before(){
        buildSupplierRequest();
        buildCrazyAirResponse();
    }

    private void buildSupplierRequest() {
        supplierRequest = new SupplierRequest();
        supplierRequest.setDepartureDate(LocalDate.of(2019,05,05));
        supplierRequest.setReturnDate(LocalDate.of(2019,05,05));
    }

    private void buildCrazyAirResponse() {
        crazyAirResponse = new CrazyAirResponse();
        crazyAirResponse.setPrice(Double.valueOf(22.2555));
    }

    @Test
    public void convertToCrazyAirRequest_checkDepartureDate(){
        CrazyAirRequest result = CrazyAirConverter.convertToCrazyAirRequest(supplierRequest);
        Assertions.assertThat(result.getDepartureDate()).isEqualTo("2019-05-05");
    }

    @Test
    public void convertToSupplierResponse_checkFare(){
        SupplierResponse result = CrazyAirConverter.convertToSupplierResponse(crazyAirResponse);
        Assertions.assertThat(result.getFare()).isEqualTo("22.26");
    }
}