package com.example.shoppingverse.Service;

import com.example.shoppingverse.Controller.CustomerController;
import com.example.shoppingverse.Dto.Request.CustomerRequestDto;
import com.example.shoppingverse.Dto.Response.CustomerResponseDto;
import com.example.shoppingverse.Model.Cart;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Repositry.CustomerRepositry;
import com.example.shoppingverse.Repositry.OrderRepositry;
import com.example.shoppingverse.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepositry customerRepositry;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

//        Customer customer = new Customer();
//        customer.setName(customerRequestDto.getName());
//        customer.setGender(customerRequestDto.getGender());
//        customer.setEmailId(customerRequestDto.getEmailId());
//        customer.setMobNo(customerRequestDto.getMobNo());

    Customer customer= CustomerTransformer.cutomerRequestDtoToCustomer(customerRequestDto);
        Cart cart= new Cart();

        cart.setTotalCart(0);
        cart.setCustomer(customer);

        Customer savedCustomer = customerRepositry.save(customer);

//        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
//                .name(savedCustomer.getName())
//                .emailId(savedCustomer.getEmailId())
//                .gender(savedCustomer.getGender())
//                .mobNo(savedCustomer.getMobNo())
//                .build();

        return CustomerTransformer.cutomerToCustomerResponseDto(savedCustomer);

        // return customerResponseDto;
    }

    public List<String> getAllFemaleCustomerAgeBwn2030(int k) {

        List<Customer> customers= customerRepositry.getAllFemaleCustomerAgeBwn2030(k);

        List<String> names= new ArrayList<>();

        for(Customer customer: customers) {
            List<OrderEntity> orderEntities = customer.getOrderEntities();

            names.add(customer.getName());
        }
        return names;

    }
}
