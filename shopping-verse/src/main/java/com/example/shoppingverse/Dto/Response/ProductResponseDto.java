package com.example.shoppingverse.Dto.Response;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    String sellerName;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory productCategory;

    ProductStatus productStatus;
}
