package com.example.fluenttest;

import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 2/28/2024 11:57 PM
 */
@Service("conditionBean")
public class ConditionBean {

    public boolean exist() {
        return true;
    }
}
