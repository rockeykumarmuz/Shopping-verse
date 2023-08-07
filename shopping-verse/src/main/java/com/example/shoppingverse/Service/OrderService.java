package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.OrderRequestDto;
import com.example.shoppingverse.Dto.Response.OrderResponseDto;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.InsufficientQuantityException;
import com.example.shoppingverse.Exception.InvalidCardException;
import com.example.shoppingverse.Exception.ProductNotFoundException;
import com.example.shoppingverse.Model.*;
import com.example.shoppingverse.Repositry.*;
import com.example.shoppingverse.Transformer.ItemTranformer;
import com.example.shoppingverse.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepositry orderRepositry;

    @Autowired
    CustomerRepositry customerRepositry;

    @Autowired
    ProductRepositry productRepositry;

    @Autowired
    ItemRepositry itemRepositry;

    @Autowired
    CardRepositry cardRepositry;

    @Autowired
    CardService cardService;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepositry.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer==null) {
            throw new CustomerNotFoundException("Sorry! customer doesn't exist.");
        }

        Optional<Product> optionalProduct= productRepositry.findById(orderRequestDto.getProductId());
        if(!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Sorry! product doesn't exist.");
        }

        Product product = optionalProduct.get();

        Card card= cardRepositry.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || todayDate.after(card.getValidTill())) {
            throw new InvalidCardException("Sorry! card doesn't exist.");
        }

        if(product.getAvailableQuantity()< orderRequestDto.getRequiredQuantity()) {
            throw new InsufficientQuantityException("Sorry! Insufficient Quantity");
        }

        // here we have placed an order so, we need to reduce the quantity of product
        int newQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);

        // if its quantity is zero  means ow it's out of stock
        if(newQuantity==0) {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }


        // prepare request dto to Entity
        OrderEntity orderEntity= new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
       // orderEntity.setOrderDate(new Date());
        orderEntity.setTotalOrder(orderRequestDto.getRequiredQuantity() * product.getPrice());
        orderEntity.setCardUsed(cardService.generatedMaskedCard(orderRequestDto.getCardUsed()));

        Item item = ItemTranformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrderEntity(orderEntity);

        // set customer into the orderEntity
        orderEntity.setCustomer(customer);
        orderEntity.getItems().add(item);

        OrderEntity savedOrderEntity = orderRepositry.save(orderEntity);
        // their is just one entity so get(0) will work fine
        product.getItems().add(savedOrderEntity.getItems().get(0));
        customer.getOrderEntities().add(savedOrderEntity);

//        Product savedProduct = productRepositry.save(product);
//        Customer savedCustomer = customerRepositry.save(customer);

        // convert an Entity to responseDto

        return OrderTransformer.orderToOrderResponseDto(savedOrderEntity);

    }

    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generatedMaskedCard(card.getCardNo()));

        int orderTotal =0;

        for(Item item: cart.getItems()) {
            Product product= item.getProduct();

            if(product.getAvailableQuantity()<item.getRequiredQuantity()) {
                throw new InsufficientQuantityException("Sorry! Insufficient Quantity available for product "+product.getProductName());
            }

            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);

            if(newQuantity==0) {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            orderTotal += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(orderEntity);
        }

        orderEntity.setTotalOrder(orderTotal);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(card.getCustomer());

        return orderEntity;
    }
}
