package com.techyos.testingdemo;

import android.annotation.SuppressLint;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.util.Calendar;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

class DisabledOnWednesdayCondition implements TestExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        return isWednesday() ? disabled("Disabled, it's that day of the week again ...") : enabled("Let's do this");
    }

    @SuppressLint("WrongConstant")
    public boolean isWednesday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
    }
}
