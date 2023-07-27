package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.ProductRequestDto;
import com.example.shoppingverse.Dto.Response.ProductResponseDto;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Exception.SellerNotFoundException;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Model.Seller;
import com.example.shoppingverse.Repositry.OrderRepositry;
import com.example.shoppingverse.Repositry.ProductRepositry;
import com.example.shoppingverse.Repositry.SellerRepositry;
import com.example.shoppingverse.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepositry productRepositry;

    @Autowired
    SellerRepositry sellerRepositry;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepositry.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller==null) {
            throw new SellerNotFoundException("Sorry! Seller doesn't exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        // it will save both seller and product because seller is the parent entity
        Seller savedSeller = sellerRepositry.save(seller);

        List<Product> products = savedSeller.getProducts();
        Product savedProduct = products.get(products.size()-1);

        // again entity to response conversion
        ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(savedProduct);

        return productResponseDto;
    }

    public List<ProductResponseDto> getProductCategoryAndPriceGreaterThan(int price,
                                                                                 ProductCategory category) {

        List<Product> products = productRepositry.getProductCategoryAndPriceGreaterThan(price, category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        // we are iterating and getting response entity
        for(Product product: products) {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
   }

}
