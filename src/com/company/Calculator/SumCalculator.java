package com.company.Calculator;

public class SumCalculator implements ICalculator {
    @Override
    public long compute(long lhs, long rhs) {
        return lhs + rhs;
    }
}
