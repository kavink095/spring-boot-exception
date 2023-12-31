package com.spring.academy.exceptionhandling.service;

import com.spring.academy.exceptionhandling.dto.CommonResponse;
import com.spring.academy.exceptionhandling.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public ResponseEntity<CommonResponse> saveCustomer(CustomerDTO customerDTO);
    public ResponseEntity<CommonResponse> findCustomer(int cusId);

    public ResponseEntity<CommonResponse> findAllCustomers();
}
