package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositry extends JpaRepository<Cart, Integer> {



}
