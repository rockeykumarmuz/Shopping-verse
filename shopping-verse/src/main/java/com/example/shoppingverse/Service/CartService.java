package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.ItemRequestDto;
import com.example.shoppingverse.Dto.Response.CartResponseDto;
import com.example.shoppingverse.Model.Cart;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Repositry.CartRepositry;
import com.example.shoppingverse.Repositry.CustomerRepositry;
import com.example.shoppingverse.Repositry.ItemRepositry;
import com.example.shoppingverse.Repositry.ProductRepositry;
import com.example.shoppingverse.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepositry cartRepositry;

    @Autowired
    ItemRepositry itemRepositry;

    @Autowired
    CustomerRepositry customerRepositry;

    @Autowired
    ProductRepositry productRepositry;

    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {

        Customer customer = customerRepositry.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepositry.findById(itemRequestDto.getProductId()).get();

        Cart cart=customer.getCart();

        System.out.println(item.toString());
        cart.setTotalCart(cart.getTotalCart() + itemRequestDto.getRequiredQuantity()*product.getPrice());

        item.setCart(cart);
        item.setProduct(product);

        // item is common in both cart and products so we will have to set first item then set into different class with saved entity
        // else results will have more duplicate value
        Item savedItem = itemRepositry.save(item);


        // we will add saved entity otherwise duplicate results will be added
        cart.getItems().add(savedItem);
        product.getItems().add(savedItem);

        // save both entity and return response
        Cart savedcart = cartRepositry.save(cart);

        productRepositry.save(product);

        // prepare cart response dto
        return CartTransformer.CartToCartResponseDto(savedcart);
    }
}
