package com.company.Calculator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorContext {
  private Map<String, ICalculator> calculators;

  CalculatorContext() {
      calculators = new HashMap<String, ICalculator>();
      calculators.put("+", new SumCalculator());
      calculators.put("-", new DiffCalculator());
      calculators.put("/", new DivisionCalculator());
      calculators.put("x", new MultiplicationCalculator());
  }

    /**
     * Computes an atomic math expression, by automatically picking the right solver.
     * @param operator operator(Sign)
     * @param lhs left hand operator
     * @param rhs right hand operator
     * @return result
     * @throws Exception if atomic operation fails( eg. division by 0)
     */
  public long compute(String operator, long lhs, long rhs) throws Exception {
    if(!calculators.keySet().contains(operator)) {
        throw new Exception();
    }
    return calculators.get(operator).compute(lhs, rhs);
  }
}
