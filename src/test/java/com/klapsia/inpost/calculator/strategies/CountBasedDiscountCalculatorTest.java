package com.klapsia.inpost.calculator.strategies;

import com.klapsia.inpost.properties.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class CountBasedDiscountCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "1,100.00",
            "2,180.00",
            "3,240.00",
            "4,280.00",
            "5,350.00",
            "100,7000.00",
    })
    void test_calculate(Long amount, Double expectedResult){
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        configuration.setCountBasedPolicy(List.of(10.0, 20.0, 30.0));
        CountBasedDiscountCalculator uut = new CountBasedDiscountCalculator(configuration);
        Double result = uut.calculate(100.00, amount);
        Assertions.assertEquals(expectedResult, result);
    }
}