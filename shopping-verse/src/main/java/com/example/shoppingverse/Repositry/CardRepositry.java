package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.Card;
import com.example.shoppingverse.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepositry extends JpaRepository<Card, Integer> {

    public Card findByCardNo(String cardNo);

}
