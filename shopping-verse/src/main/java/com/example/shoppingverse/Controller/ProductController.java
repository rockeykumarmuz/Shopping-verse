package com.example.shoppingverse.Controller;

import com.example.shoppingverse.Dto.Request.ProductRequestDto;
import com.example.shoppingverse.Dto.Response.ProductResponseDto;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Exception.SellerNotFoundException;
import com.example.shoppingverse.Service.OrderService;
import com.example.shoppingverse.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            ProductResponseDto response = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProductCategoryAndPriceGreaterThan")
    public ResponseEntity getProductCategoryAndPriceGreaterThan(@RequestParam("price") int price, @RequestParam("category")ProductCategory category) {

        List<ProductResponseDto> response = productService.getProductCategoryAndPriceGreaterThan(price, category);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }


}
