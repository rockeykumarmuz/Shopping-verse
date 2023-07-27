package com.example.shoppingverse.Service;

import com.example.shoppingverse.Dto.Request.SellerRequestDto;
import com.example.shoppingverse.Dto.Response.SellerResponseDto;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Model.Seller;
import com.example.shoppingverse.Repositry.SellerRepositry;
import com.example.shoppingverse.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepositry sellerRepositry;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        Seller seller= SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepositry.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);

    }

    public List<String> getSellerWithCheapestProduct() {
        List<Seller> sellers= sellerRepositry.getSellerWithCheapestProduct();

        int min= Integer.MAX_VALUE;
        HashMap<String, Integer> map= new HashMap<>();

        List<String > names= new ArrayList<>();

        for(Seller seller: sellers) {
            List<Product> products= seller.getProducts();

            for(Product product: products) {
                int price = product.getPrice();


                if(map.containsKey(seller.getName())) {
                    map.put(seller.getName(), map.get(seller.getName())+1);
                } else {
                    map.put(seller.getName(), 1);
                }
            }
        }


        for(String str: map.keySet()) {
            int value =map.get(str);

            if(min>value) {
                value=min;
                names.add(str);
            } else if(min==value){
                names.add(str);
            }
        }

        return names;
    }
}
