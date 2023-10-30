package com.klapsia.inpost.contract;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(@NotNull
                             @Min(1)
                             @Max(Long.MAX_VALUE)
                             Long amount, DiscountPolicy discountPolicy) {


}
