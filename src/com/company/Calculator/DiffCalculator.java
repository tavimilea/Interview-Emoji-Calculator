package com.company.Calculator;

public class DiffCalculator implements ICalculator {
    @Override
    public long compute(long lhs, long rhs) {
        return lhs - rhs;
    }
}
