package com.spring.academy.exceptionhandling.controller;

import com.spring.academy.exceptionhandling.dto.CommonResponse;
import com.spring.academy.exceptionhandling.dto.CustomerDTO;
import com.spring.academy.exceptionhandling.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("request hit to saveCustomer");
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CommonResponse> getCustomer(@PathVariable int id) {
        return customerService.findCustomer(id);
    }

    @GetMapping("/get/all")
    public ResponseEntity<CommonResponse> findAllCustomers(){
        return customerService.findAllCustomers();
    }

}
