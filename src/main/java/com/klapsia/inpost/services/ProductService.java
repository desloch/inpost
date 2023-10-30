package com.klapsia.inpost.services;


import com.klapsia.inpost.calculator.factory.CalculatorFactory;
import com.klapsia.inpost.contract.ProductPriceResponse;
import com.klapsia.inpost.contract.ProductRequest;
import com.klapsia.inpost.entities.ProductEntity;
import com.klapsia.inpost.exceptions.NotFoundException;
import com.klapsia.inpost.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final CalculatorFactory calculatorFactory;

    public Mono<ProductPriceResponse> calculateProductPrice(UUID productId, ProductRequest body) {
        return Mono.fromCallable(() -> {
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
            if (optionalProductEntity.isPresent()) {
                ProductEntity productEntity = optionalProductEntity.get();
                Double calculatedPrice = calculatorFactory.determineCalculator(body.discountPolicy()).calculate(productEntity.price(), body.amount());
                return new ProductPriceResponse(productEntity.name(), calculatedPrice, body.amount());
            }
            throw new NotFoundException();
        });
    }

}
