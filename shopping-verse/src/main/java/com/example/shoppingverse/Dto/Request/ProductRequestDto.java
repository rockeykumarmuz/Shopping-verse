package com.example.shoppingverse.Dto.Request;

import com.example.shoppingverse.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String sellerEmailId;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory productCategory;

}
