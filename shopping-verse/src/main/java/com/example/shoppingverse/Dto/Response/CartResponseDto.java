package com.example.shoppingverse.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {


    String customerName;

    int totalCart;

    List<ItemResponseDto> itemResponseDtos;

}
