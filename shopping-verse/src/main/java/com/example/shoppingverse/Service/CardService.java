package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.CardRequestDto;
import com.example.shoppingverse.Dto.Response.CardResponseDto;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Model.Card;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Repositry.CardRepositry;
import com.example.shoppingverse.Repositry.CustomerRepositry;
import com.example.shoppingverse.Transformer.CardTransformer;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepositry cardRepositry;

    @Autowired
    CustomerRepositry customerRepositry;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        // findByMobNo make always it with same variable which is defined in Customer entity otherwise will get error
        Customer customer = customerRepositry.findByMobNo(cardRequestDto.getCustomerMobNo());

        if(customer==null) {
            throw new CustomerNotFoundException("Sorry! customer doesn't exist");
        }

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);
        customer.getCard().add(card);

        // save customer because  it's a parent class
        Customer savedCustomer = customerRepositry.save(customer);

        // now prepare response dto

        List<Card> cardList = savedCustomer.getCard();
        Card savedCard = cardList.get(cardList.size()-1);

        CardResponseDto cardResponseDto = CardTransformer.CardToCardRepsonseDto(savedCard);
        cardResponseDto.setCardNo(generatedMaskedCard(savedCard.getCardNo()));
        return cardResponseDto;

    }

    // method to convert user card as sa masked card we will only show user to last 4 digits of card no else not
    public String generatedMaskedCard(String cardNo) {

        int cardLength = cardNo.length();
        String maskedCard = "";

        for(int i=0; i<cardLength-1; i++) {
            maskedCard += "X";
        }

        maskedCard +=cardNo.substring(cardLength-4);
        return maskedCard;
    }
}
