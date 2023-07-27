package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer, Integer> {

    public Customer findByMobNo(String mobNo);

    public Customer findByEmailId(String emailId);

    @Query(value = "select * from customer where age between 20 and 30 and gender=\"FEMALE\" ", nativeQuery = true)
    List<Customer> getAllFemaleCustomerAgeBwn2030(int k);
}
