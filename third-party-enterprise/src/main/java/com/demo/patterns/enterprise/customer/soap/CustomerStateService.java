package com.demo.patterns.enterprise.customer.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface CustomerStateService {
    @WebMethod
    String changeCustomerState(Long customerId, String newState);
}
