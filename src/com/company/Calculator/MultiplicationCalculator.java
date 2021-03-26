package com.company.Calculator;

public class MultiplicationCalculator implements ICalculator {
    @Override
    public long compute(long lhs, long rhs) {
        return lhs * rhs;
    }
}
