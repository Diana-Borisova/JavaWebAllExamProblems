package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionEnum;

public interface ConditionService {

    void initConditions();

    Condition findConditionByConditionEnum(ConditionEnum conditionEnum);
}
