package com.klapsia.inpost.contract;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping(
        produces = {"application/json"}
)
public interface ProductEndpoint {

    @PostMapping("/products/{id}/calculate")
    Mono<ProductPriceResponse> calculatePrice(@PathVariable("id") @NotNull UUID id, @Valid @RequestBody ProductRequest request);

}
