package com.klapsia.inpost.calculator.strategies;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.*;

@AllArgsConstructor
public abstract class AbstractPriceCalculator {

    abstract Double calculatePrice(Double price, Long amount);

    private Double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public Double calculate(Double price, Long amount) {
        return round(calculatePrice(price, amount));
    }

}
