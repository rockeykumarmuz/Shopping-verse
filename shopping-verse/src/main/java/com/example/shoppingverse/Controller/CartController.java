package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Dto.Request.CheckoutCartRequestDto;
import com.example.shoppingverse.Dto.Request.ItemRequestDto;
import com.example.shoppingverse.Dto.Request.OrderRequestDto;
import com.example.shoppingverse.Dto.Response.CartResponseDto;
import com.example.shoppingverse.Dto.Response.OrderResponseDto;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Service.CartService;
import com.example.shoppingverse.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) {

        try {
            Item item = itemService.CreateItem(itemRequestDto);

            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto, item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/placeOrder")
    public ResponseEntity checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) {
        try{
            OrderResponseDto responseDto = cartService.checkOutCart(checkoutCartRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
