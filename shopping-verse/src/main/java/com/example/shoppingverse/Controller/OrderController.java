package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;



}
