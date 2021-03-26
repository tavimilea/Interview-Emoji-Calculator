package com.company.Calculator;

public class DivisionCalculator implements ICalculator {
    @Override
    public long compute(long lhs, long rhs) throws Exception {
        if(rhs == 0) {
          throw new Exception();
        }
        return lhs / rhs;
    }
}
