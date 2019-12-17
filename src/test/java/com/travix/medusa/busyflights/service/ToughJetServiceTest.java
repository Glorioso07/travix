package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.converter.ToughJetConverter;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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

@PrepareForTest(ToughJetConverter.class)
@RunWith(PowerMockRunner.class)
public class ToughJetServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ToughJetResponse toughJetResponse;

    @Mock
    private SupplierRequest supplierRequest;

    @Mock
    private SupplierResponse supplierResponse;

    private ToughJetRequest toughJetRequest;

    private ToughJetServiceImpl toughJetServiceImpl;

    @Before
    public void before(){
        buildToughJetRequest();
        buildRestCall();
        buildToughJetService();
        buildToughJetConverter();
    }

    private void buildToughJetRequest() {
        toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom("SEV");
        toughJetRequest.setTo("LON");
        toughJetRequest.setInboundDate("2019-05-05");
        toughJetRequest.setOutboundDate("2019-05-05");
        toughJetRequest.setNumberOfAdults(3);
    }

    private void buildRestCall() {
        when(restTemplate.getForObject("http://localhost:9000/toughjet/flights?from=SEV&to=LON&outboundDate=2019-05-05&inboundDate=2019-05-05&numberOfAdults=3",
                ToughJetResponse[].class)).thenReturn(new ToughJetResponse[]{ toughJetResponse });
    }

    private void buildToughJetService() {
        toughJetServiceImpl = new ToughJetServiceImpl(restTemplate);
        Whitebox.setInternalState(toughJetServiceImpl,"TOUGHJET_SERVER_URL","http://localhost:9000");
        Whitebox.setInternalState(toughJetServiceImpl,"TOUGHJET_CONTROLLER","toughjet");
        Whitebox.setInternalState(toughJetServiceImpl,"TOUGHJET_RESOURCE","flights");
    }

    private void buildToughJetConverter() {
        PowerMockito.mockStatic(ToughJetConverter.class);
        when(ToughJetConverter.convertToToughJetRequest(supplierRequest)).thenReturn(toughJetRequest);
        when(ToughJetConverter.convertToSupplierResponse(toughJetResponse)).thenReturn(supplierResponse);
    }

    @Test
    public void getFlights_checkSizeResponse(){
        List<SupplierResponse> result = toughJetServiceImpl.getFlights(supplierRequest);
        Assertions.assertThat(result).contains(supplierResponse);
    }

}