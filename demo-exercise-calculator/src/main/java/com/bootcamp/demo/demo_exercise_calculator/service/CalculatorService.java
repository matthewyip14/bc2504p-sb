package com.bootcamp.demo.demo_exercise_calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_exercise_calculator.lib.Operation;
import com.bootcamp.demo.demo_exercise_calculator.model.Calculator;

@Service
public class CalculatorService {

public Object calculate(double x, double y, String rawOperation) {
    Operation operation = Operation.fromString(rawOperation);
    switch (operation) {
        case ADD: return x + y;
        case SUB: return x - y;
        case MUL: return BigDecimal.valueOf(x)
                .multiply(BigDecimal.valueOf(y))
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();
        case DIV:
            Double divisor = BigDecimal.valueOf(y).doubleValue();
            if (divisor == 0) throw new IllegalArgumentException("Division by zero");
            return BigDecimal.valueOf(x)
                .divide(BigDecimal.valueOf(divisor), 5, RoundingMode.HALF_UP)
                .doubleValue();
        default:
            throw new IllegalStateException("Unexpected operation: " + operation);
    }
}
}
