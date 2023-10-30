package com.klapsia.inpost.calculator.factory;


import com.klapsia.inpost.calculator.strategies.AbstractPriceCalculator;
import com.klapsia.inpost.calculator.strategies.CountBasedDiscountCalculator;
import com.klapsia.inpost.calculator.strategies.PercentageBasedCalculator;
import com.klapsia.inpost.calculator.strategies.PriceCalculator;
import com.klapsia.inpost.contract.DiscountPolicy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalculatorFactory {

    private final CountBasedDiscountCalculator countBasedDiscountCalculator;
    private final PriceCalculator priceCalculator;
    private final PercentageBasedCalculator percentageBasedCalculator;

    public AbstractPriceCalculator determineCalculator(DiscountPolicy policy) {
        if(policy == null){
            //from java 21 could be like below, but intellij is not helping
            return priceCalculator;
        }
        return switch(policy){
            case COUNT_BASED -> countBasedDiscountCalculator;
            case PERCENTAGE_BASED -> percentageBasedCalculator;
//            case null -> priceCalculator;
            default -> priceCalculator;
        };
    }

}
