package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.Dto.Request.CardRequestDto;
import com.example.shoppingverse.Dto.Response.CardResponseDto;
import com.example.shoppingverse.Model.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto) {
        return Card.builder().
             cardNo(cardRequestDto.getCardNo())
                     .cardType(cardRequestDto.getCardType())
                             .validTill(cardRequestDto.getValidTill())
                                     .cvv(cardRequestDto.getCvv()).
                build();
    }

    public static CardResponseDto CardToCardRepsonseDto(Card card) {
        return CardResponseDto.builder().
                customerName(card.getCustomer().getName())
//                        .cardNo(card.getCardNo()) it cannot be set here it will be set through mass generator
                                .validTill(card.getValidTill())
                                        .cardType(card.getCardType()).
                build();


    }
}
