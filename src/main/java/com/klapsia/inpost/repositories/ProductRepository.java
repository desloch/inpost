package com.klapsia.inpost.repositories;

import com.klapsia.inpost.entities.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ProductRepository {

    static Map<UUID, ProductEntity> products = new HashMap<>();

    static {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(new ProductEntity(UUID.fromString("e6b577db-7102-4384-813c-bb0b5252ea43"), "abc", Double.valueOf("10.00")));
        productList.add(new ProductEntity(UUID.randomUUID(), "xyz", Double.valueOf("12.10")));
        productList.add(new ProductEntity(UUID.randomUUID(), "dce", Double.valueOf("0.23")));
        products = productList.stream().collect(Collectors.toMap(a -> a.id(), Function.identity()));
        log.info("\n\nAvailable products \n{}\n\n", productList);
    }

    public Optional<ProductEntity> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }


}
