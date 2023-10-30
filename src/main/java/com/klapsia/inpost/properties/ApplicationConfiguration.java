package com.klapsia.inpost.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
public class ApplicationConfiguration {

    @Value("#{'${count.based.policy}'.split(',')}")
    private List<Double> countBasedPolicy;

    @Value("${percentage.based.policy}")
    private Double percentageBasedPolicy;
}
