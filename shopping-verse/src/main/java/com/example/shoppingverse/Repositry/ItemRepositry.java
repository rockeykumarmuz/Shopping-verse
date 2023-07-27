package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositry extends JpaRepository<Item, Integer> {
}
