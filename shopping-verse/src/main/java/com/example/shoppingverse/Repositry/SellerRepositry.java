package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepositry extends JpaRepository<Seller, Integer> {

    public Seller findByEmailId(String email);


    @Query(value =  "select * from seller", nativeQuery = true)
    public List<Seller> getSellerWithCheapestProduct();
}
