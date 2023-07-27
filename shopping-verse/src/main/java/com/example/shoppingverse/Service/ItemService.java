package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.ItemRequestDto;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.InsufficientQuantityException;
import com.example.shoppingverse.Exception.ProductNotFoundException;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Repositry.CustomerRepositry;
import com.example.shoppingverse.Repositry.ItemRepositry;
import com.example.shoppingverse.Repositry.ProductRepositry;
import com.example.shoppingverse.Transformer.ItemTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepositry itemRepositry;

    @Autowired
    CustomerRepositry customerRepositry;

    @Autowired
    ProductRepositry productRepositry;

    public Item CreateItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepositry.findByEmailId(itemRequestDto.getCustomerEmail());

        if(customer==null) {
            throw new CustomerNotFoundException("Sorry! customer doesn't exist");
        }

        Optional<Product> optionalProduct = productRepositry.findById(itemRequestDto.getProductId());

        if(!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Sorry! product doesn't exist");
        }

        // getting product if it exist
        Product product=optionalProduct.get();

        if(itemRequestDto.getRequiredQuantity()>product.getAvailableQuantity()) {
            throw new InsufficientQuantityException("sorry! Insufficient item");
        }

        Item item = ItemTranformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;

    }
}
