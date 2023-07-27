package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepositry extends JpaRepository<OrderEntity, Integer> {

}
