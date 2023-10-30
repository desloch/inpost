package com.klapsia.inpost.calculator.strategies;

import com.klapsia.inpost.properties.ApplicationConfiguration;
import org.springframework.stereotype.Component;

@Component
public class PercentageBasedCalculator extends AbstractPriceCalculator {

    private final Double percentageDiscount;

    public PercentageBasedCalculator(ApplicationConfiguration configuration) {
        this.percentageDiscount = configuration.getPercentageBasedPolicy();
    }

    @Override
    Double calculatePrice(Double price, Long amount) {
        return price * amount * ((100.0 - percentageDiscount) / 100.0);
    }
}
