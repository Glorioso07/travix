package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.supplier.SupplierRequest;
import com.travix.medusa.busyflights.domain.supplier.SupplierResponse;

import java.util.List;

public interface SupplierService {
     List<SupplierResponse> getFlights(SupplierRequest request);
}
