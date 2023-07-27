package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Response.ItemResponseDto;
import com.example.shoppingverse.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemTranformer {

    public static Item ItemRequestDtoToItem(int requiredQuantity) {
        return Item.builder().
                requiredQuantity(requiredQuantity).
                build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {

        return ItemResponseDto.builder().
                itemName(item.getProduct().getProductName())
                        .price(item.getProduct().getPrice())
                                .itemAdded(item.getRequiredQuantity())
                                        .category(item.getProduct().getProductCategory()).
                build();
    }
}
