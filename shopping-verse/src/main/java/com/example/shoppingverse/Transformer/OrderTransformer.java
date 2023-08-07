package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Response.ItemResponseDto;
import com.example.shoppingverse.Dto.Response.OrderResponseDto;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

//
//import com.example.shoppingverse.Dto.Request.OrderRequestDto;
//import com.example.shoppingverse.Model.OrderEntity;
//import com.example.shoppingverse.Model.Product;
//import com.example.shoppingverse.Repositry.ProductRepositry;
//import com.example.shoppingverse.Service.CardService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//import java.util.UUID;
//
public class OrderTransformer {
//
//    @Autowired
//    ProductRepositry productRepositry;
//
//    // just to encrypt card no
//    public static String generatedMaskedCard(String cardNo) {
//
//        int cardLength = cardNo.length();
//        String maskedCard = "";
//
//        for(int i=0; i<cardLength-1; i++) {
//            maskedCard += "X";
//        }
//
//        maskedCard +=cardNo.substring(cardLength-4);
//        return maskedCard;
//    }
//    public static OrderEntity orderRequestDtoToOrderEntity(OrderRequestDto orderRequestDto) {
//
//        Product product= product
//
//        return OrderEntity.builder()
//                        .orderId(String.valueOf(UUID.randomUUID())).
//        cardUsed(generatedMaskedCard(orderRequestDto.getCardUsed()))
//                .orderDate(new Date())
//                        .totalOrder(orderRequestDto.getRequiredQuantity() * )
//                build();
//
//    }
//}

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity savedOrderEntity) {

        List<ItemResponseDto> itemList = new ArrayList<>();
        for(Item item: savedOrderEntity.getItems()) {
            itemList.add(ItemTranformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder().
                orderId(savedOrderEntity.getOrderId())
                        .orderDate(savedOrderEntity.getOrderDate())
                                .cardUsed(savedOrderEntity.getCardUsed())
                                        .totalOrder(savedOrderEntity.getTotalOrder())
                                                .customerName(savedOrderEntity.getCustomer().getName())
                                                        .itemResponseDtoList(itemList).
                build();
    }
}
