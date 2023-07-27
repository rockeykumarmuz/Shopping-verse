package com.example.shoppingverse.Dto.Request;

import com.example.shoppingverse.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    String sellerEmailId;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory productCategory;

}
