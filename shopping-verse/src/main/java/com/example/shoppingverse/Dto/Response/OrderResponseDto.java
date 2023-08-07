package com.example.shoppingverse.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderId;

    Date orderDate;

    String cardUsed;

    int totalOrder;

    String customerName;

    List<ItemResponseDto> itemResponseDtoList;


}
