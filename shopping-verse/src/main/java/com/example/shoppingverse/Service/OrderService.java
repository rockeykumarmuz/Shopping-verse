package com.example.shoppingverse.Service;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Repositry.OrderRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepositry orderRepositry;

}
