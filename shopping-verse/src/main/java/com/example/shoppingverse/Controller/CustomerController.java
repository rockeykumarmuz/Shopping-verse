package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Dto.Request.CustomerRequestDto;
import com.example.shoppingverse.Dto.Response.CustomerResponseDto;
import com.example.shoppingverse.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto response = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
