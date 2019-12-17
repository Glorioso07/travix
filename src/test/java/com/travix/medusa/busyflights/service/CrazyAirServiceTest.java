package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.converter.CrazyAirConverter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(CrazyAirConverter.class)
@RunWith(PowerMockRunner.class)
public class CrazyAirServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CrazyAirResponse crazyAirResponse;

    @Mock
    private SupplierRequest supplierRequest;

    @Mock
    private SupplierResponse supplierResponse;

    private CrazyAirRequest crazyAirRequest;

    private CrazyAirServiceImpl crazyAirServiceImpl;

    @Before
    public void before(){
        buildCrazyAirRequest();
        buildRestCall();
        buildCrazyAirService();
        buildCrazyAirConverter();
    }

    private void buildCrazyAirRequest() {
        crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setOrigin("SEV");
        crazyAirRequest.setDestination("LON");
        crazyAirRequest.setDepartureDate("2019-05-05");
        crazyAirRequest.setReturnDate("2019-05-05");
        crazyAirRequest.setPassengerCount(3);
    }

    private void buildRestCall() {
        when(restTemplate.getForObject("http://localhost:9000/crazyair/flights?origin=SEV&destination=LON&departureDate=2019-05-05&returnDate=2019-05-05&passengerCount=3",
                CrazyAirResponse[].class)).thenReturn(new CrazyAirResponse[]{ crazyAirResponse });
    }

    private void buildCrazyAirService() {
        crazyAirServiceImpl = new CrazyAirServiceImpl(restTemplate);
        Whitebox.setInternalState(crazyAirServiceImpl,"CRAZYAIR_SERVER_URL","http://localhost:9000");
        Whitebox.setInternalState(crazyAirServiceImpl,"CRAZYAIR_CONTROLLER","crazyair");
        Whitebox.setInternalState(crazyAirServiceImpl,"CRAZYAIR_RESOURCE","flights");
    }

    private void buildCrazyAirConverter() {
        PowerMockito.mockStatic(CrazyAirConverter.class);
        when(CrazyAirConverter.convertToCrazyAirRequest(supplierRequest)).thenReturn(crazyAirRequest);
        when(CrazyAirConverter.convertToSupplierResponse(crazyAirResponse)).thenReturn(supplierResponse);
    }

    @Test
    public void getFlights_checkSizeResponse(){
        List<SupplierResponse> result = crazyAirServiceImpl.getFlights(supplierRequest);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
}