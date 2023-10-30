package com.klapsia.inpost.resource;

import com.klapsia.inpost.contract.DiscountPolicy;
import com.klapsia.inpost.contract.ProductPriceResponse;
import com.klapsia.inpost.contract.ProductRequest;
import com.klapsia.inpost.entities.ProductEntity;
import com.klapsia.inpost.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "percentage.based.policy=50",
                "count.based.policy=10,20,50"
               })
class ProductResourceTest {

    private final static UUID id = UUID.randomUUID();
    public static final String TEST_NAME = "test-name";
    @Autowired
    private WebTestClient webClient;
    @MockBean
    private ProductRepository repository;

    @BeforeEach
    void init(){
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(new ProductEntity(id, TEST_NAME, Double.valueOf("12.22"))));
    }

    @Test
    void calculate_invalid_uuid(){
        webClient
                .post().uri("/products/invalidId/calculate")
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @ParameterizedTest
    @CsvSource(value = {"null,122.2", "COUNT_BASED,61.1", "PERCENTAGE_BASED,61.1"}, nullValues = "null")
    void calculate_valid(DiscountPolicy policy, double calculatedValue){
        webClient
                .post().uri(String.format("/products/%s/calculate", id))
                .bodyValue(new ProductRequest(10L, policy))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductPriceResponse.class)
                .isEqualTo(new ProductPriceResponse(TEST_NAME, calculatedValue, 10l));
    }
}