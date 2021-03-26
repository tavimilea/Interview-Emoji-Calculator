package com.company.Calculator;

public interface ICalculator {
    /**
     * Interface method for exposing Strategy Pattern foreach class
     * that will provide a functionality for computing simple(atomic) operations
     * @param lhs left hand operand
     * @param rhs right hand operator
     * @return result
     * @throws Exception if  the simple operation fails(eg. division by 0)
     */
    long compute(long lhs, long rhs) throws Exception;
}
