package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Response.CartResponseDto;
import com.example.shoppingverse.Dto.Response.ItemResponseDto;
import com.example.shoppingverse.Model.Cart;
import com.example.shoppingverse.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart) {

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for (Item item: cart.getItems()) {
            itemResponseDtoList.add(ItemTranformer.ItemToItemResponseDto(item));
        }


        return CartResponseDto.builder().
                customerName(cart.getCustomer().getName())
                        .totalCart(cart.getTotalCart())
                                .itemResponseDtos(itemResponseDtoList).
                build();

    }
}
