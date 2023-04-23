package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.ConditionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    private final ModelMapper modelMapper;

    public ConditionServiceImpl(ConditionRepository conditionRepository, ModelMapper modelMapper) {
        this.conditionRepository = conditionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initConditions() {
        if (this.conditionRepository.count() == 0){
            Arrays.stream(ConditionEnum.values())
                    .forEach(conditionEnum -> {
                        Condition condition = new Condition();
                       condition.setConditionEnum(conditionEnum);
                        switch (conditionEnum){
                            case EXCELLENT -> condition.setDescription("In perfect condition");
                            case GOOD -> condition.setDescription("Some signs of wear and tear or minor defects");
                            case ACCEPTABLE -> condition.setDescription("The item is fairly worn but continues to function properly");

                        }
                       conditionRepository.save(condition);
                    });

        }
        }
    @Override
    public Condition findConditionByConditionEnum(ConditionEnum conditionEnum) {
        return conditionRepository.findConditionByConditionEnum(conditionEnum).orElseThrow();
    }
}
