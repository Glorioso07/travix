package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ToughJetConverterTest {

    private SupplierRequest supplierRequest;
    private ToughJetResponse toughJetResponse;

    @Before
    public void before(){
        buildSupplierRequest();
        buildToughJetResponse();
    }

    private void buildSupplierRequest() {
        supplierRequest = new SupplierRequest();
        supplierRequest.setDepartureDate(LocalDate.of(2019,05,05));
        supplierRequest.setReturnDate(LocalDate.of(2019,05,05));
        supplierRequest.setNumberOfPassengers(3);
    }

    private void buildToughJetResponse() {
        toughJetResponse = new ToughJetResponse();
        toughJetResponse.setInboundDateTime("2019-05-05T00:00:00");
    }

    @Test
    public void convertToToughJetRequest_checkNumberOfAdults(){
        ToughJetRequest result = ToughJetConverter.convertToToughJetRequest(supplierRequest);
        Assertions.assertThat(result.getNumberOfAdults()).isEqualTo(3);
    }

    @Test
    public void convertToSupplierResponse_checkArrivalDate(){
        SupplierResponse result = ToughJetConverter.convertToSupplierResponse(toughJetResponse);
        Assertions.assertThat(result.getArrivalDate()).isEqualTo("2019-05-05T00:00:00");
    }
}