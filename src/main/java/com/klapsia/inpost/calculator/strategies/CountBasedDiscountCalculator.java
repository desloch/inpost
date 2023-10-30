package com.klapsia.inpost.calculator.strategies;

import com.klapsia.inpost.properties.ApplicationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CountBasedDiscountCalculator extends AbstractPriceCalculator {

    private final List<Double> amountBasedPolicy;

    public CountBasedDiscountCalculator(ApplicationConfiguration configuration) {
        this.amountBasedPolicy = configuration.getCountBasedPolicy();
    }

    @Override
    Double calculatePrice(Double price, Long amount) {
        if (amount > 1) {
            int index = amount.intValue() - 2;
            if (amount > amountBasedPolicy.size()) {
                index = amountBasedPolicy.size() - 1;
            }
            return price * amount * ((100.0 - amountBasedPolicy.get(index)) / 100.0);
        }
        return price;
    }
}
