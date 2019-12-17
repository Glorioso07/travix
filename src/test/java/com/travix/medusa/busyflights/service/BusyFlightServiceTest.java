package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BusyFlightServiceTest {

    private List<SupplierService> supplierServices;
    private BusyFlightServiceImpl busyFlightServiceImpl;

    @Before
    public void before() {
        buildSupplierServices();
        buildFlightServiceImpl();
    }

    private void buildSupplierServices(){
        supplierServices = new ArrayList<>();
        supplierServices.add(new CrazyAirSupplierService());
        supplierServices.add(new ToughJetSupplierService());
    }

    private void buildFlightServiceImpl(){
        busyFlightServiceImpl = new BusyFlightServiceImpl(supplierServices);
    }

    @Test
    public void getFlightsOrderedByFare_checkOrder(){
        List<BusyFlightsResponse> result = busyFlightServiceImpl.getFlightsOrderedByFare(new BusyFlightsRequest());
        Assertions.assertThat(Integer.valueOf(result.get(0).getFare())).isEqualTo(20);
        Assertions.assertThat(Integer.valueOf(result.get(1).getFare())).isEqualTo(21);
        Assertions.assertThat(Integer.valueOf(result.get(2).getFare())).isEqualTo(23);
        Assertions.assertThat(Integer.valueOf(result.get(3).getFare())).isEqualTo(24);
    }

    private class CrazyAirSupplierService implements SupplierService {
        @Override
        public List<SupplierResponse> getFlights(SupplierRequest request) {
            List<SupplierResponse> flights = new ArrayList<>();
            SupplierResponse response1 = new SupplierResponse();
            response1.setFare("24");
            flights.add(response1);
            SupplierResponse response2 = new SupplierResponse();
            response2.setFare("21");
            flights.add(response2);
            SupplierResponse response3 = new SupplierResponse();
            response3.setFare("20");
            flights.add(response3);
            return flights;
        }
    }

    private class ToughJetSupplierService implements SupplierService {
        @Override
        public List<SupplierResponse> getFlights(SupplierRequest request) {
            List<SupplierResponse> flights = new ArrayList<>();
            SupplierResponse response1 = new SupplierResponse();
            response1.setFare("23");
            flights.add(response1);
            SupplierResponse response2 = new SupplierResponse();
            response2.setFare("25");
            flights.add(response2);
            return flights;
        }
    }
}