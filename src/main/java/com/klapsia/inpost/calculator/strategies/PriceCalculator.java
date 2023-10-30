package com.klapsia.inpost.calculator.strategies;

import org.springframework.stereotype.Component;

@Component
public class PriceCalculator extends AbstractPriceCalculator {

    @Override
    Double calculatePrice(Double price, Long amount) {
        return price * amount;
    }
}
