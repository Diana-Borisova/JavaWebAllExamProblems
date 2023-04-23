package com.resellerapp.init;


import com.resellerapp.service.ConditionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final ConditionService conditionService;

    public DataBaseInit(ConditionService conditionService) {
        this.conditionService = conditionService;
    }


    @Override
    public void run(String... args) throws Exception{
        this.conditionService.initConditions();
    }
}
