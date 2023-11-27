package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.CheckoutCartRequestDto;
import com.example.shoppingverse.Dto.Request.ItemRequestDto;
import com.example.shoppingverse.Dto.Response.CartResponseDto;
import com.example.shoppingverse.Dto.Response.OrderResponseDto;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.EmptyCartException;
import com.example.shoppingverse.Exception.InvalidCardException;
import com.example.shoppingverse.Model.*;
import com.example.shoppingverse.Repositry.*;
import com.example.shoppingverse.Transformer.CartTransformer;
import com.example.shoppingverse.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    OrderService orderService;

    @Autowired
    CardRepositry cardRepositry;

    @Autowired
    OrderRepositry orderRepositry;

    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {

        Customer customer = customerRepositry.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepositry.findById(itemRequestDto.getProductId()).get();

        Cart cart=customer.getCart();

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

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) {

        Customer customer = customerRepositry.findByEmailId(checkoutCartRequestDto.getCustomerEmail());
        if(customer==null) {
            throw new CustomerNotFoundException("Sorry! customer doesn't exist");
        }

        Card card  = cardRepositry.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date currentDate = new Date();
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || currentDate.after(card.getValidTill())) {
            throw new InvalidCardException("Sorry! Invalid card");
        }

        // checking cart is empty or not if empty then no need to place an order just throw exception
        Cart cart  = customer.getCart();
        if(cart.getItems().size()==0) {
            throw new EmptyCartException("Sorry! cart is empty");
        }

        OrderEntity orderEntity = orderService.placeOrder(cart, card);
        // when we order all from cart then reset it
        resetCart(cart);

        OrderEntity savedOrderEntity = orderRepositry.save(orderEntity);

        return OrderTransformer.orderToOrderResponseDto(savedOrderEntity);


    }

    public void resetCart(Cart cart) {

        cart.setTotalCart(0);

        // once  i have placed order so this item not belongs to any cart so before reset cart just do set item,setCart(null)
        // now it not belongs to any cart then set cart.setItems(null)

        for(Item item: cart.getItems()) {
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }

}
