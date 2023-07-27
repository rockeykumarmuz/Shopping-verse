package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Dto.Request.SellerRequestDto;
import com.example.shoppingverse.Dto.Response.SellerResponseDto;
import com.example.shoppingverse.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        SellerResponseDto response = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
