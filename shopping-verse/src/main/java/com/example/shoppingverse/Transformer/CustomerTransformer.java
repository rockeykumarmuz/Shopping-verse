package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Request.CustomerRequestDto;
import com.example.shoppingverse.Dto.Response.CustomerResponseDto;
import com.example.shoppingverse.Model.Customer;

public class CustomerTransformer {

    public static Customer cutomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {

        return Customer.builder().name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto cutomerToCustomerResponseDto(Customer customer) {

        return CustomerResponseDto.builder().name(customer.getName())
                .emailId(customer.getEmailId())
                .gender(customer.getGender())
                .mobNo(customer.getMobNo())
                .build();
    }
}
