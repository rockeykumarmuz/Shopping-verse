package com.example.shoppingverse.Repositry;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Integer> {

//    @Query(value = "select p from Product p order by p.productCategory = :category, desc limit 5", nativeQuery = true)
//    public List<Product> getTop5Cheapest(ProductCategory category);

    @Query("select p from Product p where p.price >= :price and p.productCategory = :productCategory")
    public List<Product> getProductCategoryAndPriceGreaterThan(int price, ProductCategory productCategory);

}
