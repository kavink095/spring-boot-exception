package com.spring.academy.exceptionhandling.service.impl;

import com.spring.academy.exceptionhandling.config.UserStatus;
import com.spring.academy.exceptionhandling.dto.CommonResponse;
import com.spring.academy.exceptionhandling.dto.CustomerDTO;
import com.spring.academy.exceptionhandling.entity.Customer;
import com.spring.academy.exceptionhandling.exception.InternalServerExceptionHandler;
import com.spring.academy.exceptionhandling.exception.RecordExistingException;
import com.spring.academy.exceptionhandling.exception.ResourceNotFoundException;
import com.spring.academy.exceptionhandling.repository.CustomerRepository;
import com.spring.academy.exceptionhandling.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<CommonResponse> saveCustomer(CustomerDTO customerDTO) {
        try {

            if (customerRepository.existsByName(customerDTO.getName())) {
                throw new RecordExistingException("User name already exists !");
            } else {
                customerRepository.save(Customer.builder()
                        .name(customerDTO.getName())
                        .address(customerDTO.getAddress())
                        .contactNumber(customerDTO.getContactNumber())
                        .activeStatus(String.valueOf(UserStatus.ACTIVE))
                        .build());

                return new ResponseEntity<>(CommonResponse.builder()
                        .message("successfully saved customer")
                        .responseCode(HttpStatus.CREATED)
                        .build(), HttpStatus.CREATED);
            }

        } catch (Exception ex) {
            log.error("failed insert new user {} Exception ", customerDTO.getName(), ex);
            return new ResponseEntity<>(CommonResponse.builder()
                    .message("Failed to save customer")
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<CommonResponse> findCustomer(int cusId) {
        try {
            if (customerRepository.existsById(cusId)) {
                Customer customerByCusId = customerRepository.findCustomerByCusId(cusId);
                return ResponseEntity.ok(CommonResponse.builder()
                        .data(CustomerDTO.builder()
                                .activeStatus(customerByCusId.getActiveStatus())
                                .address(customerByCusId.getAddress())
                                .name(customerByCusId.getName())
                                .cusId(customerByCusId.getCusId())
                                .build())
                        .message("Successfully get customer details !")
                        .build());
            } else {
                throw new ResourceNotFoundException("Resource Not Found");
            }

        } catch (Exception ex) {
            log.error("not found user for ID {} exception ", cusId, ex);
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> findAllCustomers() {
        try {

            List<CustomerDTO> customerList = customerRepository.findAll()
                    .stream()
//                    .filter(customer -> customer.getActiveStatus() == null) // Add filter for active customers
                    .map(customer -> CustomerDTO.builder()
                            .cusId(customer.getCusId())
                            .name(customer.getName())
                            .address(customer.getAddress())
                            .contactNumber(customer.getContactNumber())
                            .activeStatus(customer.getActiveStatus())
                            .build())
                    .toList();


            return ResponseEntity.ok(CommonResponse.builder()
                    .data(customerList)
                    .message("Retrieved all customers successfully")
                    .responseCode(HttpStatus.OK)
                    .build());

        } catch (Exception e) {
            log.error("Exception  ", e);
            throw new InternalServerExceptionHandler("All customers retrieved failed !");
        }
    }

}
