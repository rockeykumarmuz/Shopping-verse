package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Request.ProductRequestDto;
import com.example.shoppingverse.Dto.Response.ProductResponseDto;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto) {

        return Product.builder().
                productName(productRequestDto.getProductName()).
                price(productRequestDto.getPrice()).
                availableQuantity(productRequestDto.getAvailableQuantity()).
                productCategory(productRequestDto.getProductCategory()).
                productStatus(ProductStatus.AVAILABLE).
        build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product) {
        return ProductResponseDto.builder().
                sellerName(product.getSeller().getName()).
                productName(product.getProductName()).
                price(product.getPrice()).
                availableQuantity(product.getAvailableQuantity()).
                productCategory(product.getProductCategory()).
                productStatus(product.getProductStatus()).
                build();
    }
}
