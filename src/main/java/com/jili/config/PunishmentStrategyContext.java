/*
package com.jili.config;

import com.jili.service.RatingAntimoneyLevelService;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class PunishmentStrategyContext {

    private final Map<String, RatingAntimoneyLevelService> riskStrategyMap = new ConcurrentHashMap<>();

    public PunishmentStrategyContext(Map<String, RatingAntimoneyLevelService> strategyMap) {
        this.riskStrategyMap.putAll(strategyMap);
    }

    public RatingAntimoneyLevelService getInstance(String strategyName) {
        return riskStrategyMap.get(strategyName);
    }
}
*/
