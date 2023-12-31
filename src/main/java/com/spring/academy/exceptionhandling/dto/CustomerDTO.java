package com.spring.academy.exceptionhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private int cusId;
    private String name;
    private String address;
    private String contactNumber;
    private String activeStatus;
}
