package com.klapsia.inpost.resource;

import com.klapsia.inpost.contract.ProductEndpoint;
import com.klapsia.inpost.contract.ProductPriceResponse;
import com.klapsia.inpost.contract.ProductRequest;
import com.klapsia.inpost.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Validated
public class ProductResource implements ProductEndpoint {

    private final ProductService productService;

    @Override
    public Mono<ProductPriceResponse> calculatePrice(UUID id, ProductRequest request) {
        return
                productService.calculateProductPrice(id, request);
    }
}
