package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.SupplierEnum;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class BusyFlightConverterTest {

    private SupplierResponse supplierResponse;
    private BusyFlightsRequest busyFlightsRequest;

    @Before
    public void before(){
        buildSupplierResponse();
        buildBusyFlightsRequest();
    }

    private void buildSupplierResponse() {
        supplierResponse = new SupplierResponse();
        supplierResponse.setSupplier(SupplierEnum.ToughJet.name());
    }

    private void buildBusyFlightsRequest() {
        busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin("SEV");
        busyFlightsRequest.setDestination("ALM");
        busyFlightsRequest.setDepartureDate(LocalDate.of(2019,05,05));
        busyFlightsRequest.setReturnDate(LocalDate.of(2019,9,21));
        busyFlightsRequest.setNumberOfPassengers(2);
    }

    @Test
    public void convertToBusyFlightsResponse_checkSupplier(){
        BusyFlightsResponse result = BusyFlightsConverter.convertToBusyFlightsResponse(supplierResponse);
        Assertions.assertThat(result.getSupplier()).isEqualTo("ToughJet");
    }

    @Test
    public void convertToSupplierRequest_checkDestination(){
        SupplierRequest result = BusyFlightsConverter.convertToSupplierRequest(busyFlightsRequest);
        Assertions.assertThat(result.getDestination()).isEqualTo("ALM");
    }
}