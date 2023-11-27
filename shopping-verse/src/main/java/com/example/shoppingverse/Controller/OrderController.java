package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Dto.Request.OrderRequestDto;
import com.example.shoppingverse.Dto.Response.OrderResponseDto;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            OrderResponseDto responseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity cancelOrder(@RequestBody OrderRequestDto orderRequestDto) {
//
//    }



}
