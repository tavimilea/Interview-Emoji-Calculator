package com.company.Calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ExpressionSolver {
    private Stack<String> operatorsStack;
    private Stack<Long> operandsStack;
    private Set<String> higherPrecedence;
    private CalculatorContext ctx;

    public ExpressionSolver() {
        this.ctx = new CalculatorContext();
        this.operandsStack = new Stack<Long>();
        this.operatorsStack = new Stack<String>();
        this.higherPrecedence = new HashSet<String>();
        this.higherPrecedence.add("x");
        this.higherPrecedence.add("/");
    }

    /**
     * Given an operands and operators list, will compute the output of the operation.
     * Steps:
     *  1. Apply a brief modification of the Shunting-yard algorithm See:
     *                  https://en.wikipedia.org/wiki/Shunting-yard_algorithm .
     *      The modification consists in using two stacks, one for operands, one for operators, if a higher precedence
     *      operation is encountered, it is first computed and then added to the stack of operators.
     *      If a low precedence operation is encountered, place operator in its stack, operand/s in it's stack.
     *  2. Go through the remaining stack after all the input is consumed, and apply operators.
     * @param operators list of strings representing operators
     * @param operands list of strings representing operands
     * @return result of the operation
     * @throws Exception if operations cannot be applied
     */
    public long solveFor(List<String> operators, List<String> operands) throws Exception {
        if(operands.size() == 1) {
            return Long.parseLong(operands.get(0));
        }
        for(int i = 0; i < operators.size(); i ++) {
            String operator = operators.get(i);
            if(higherPrecedence.contains(operators.get(i))) {
                if (operandsStack.isEmpty()) {
                    long result = ctx.compute(operator,
                            Long.parseLong(operands.get(i)),
                            Long.parseLong(operands.get(i + 1)));
                    operandsStack.push(result);
                } else {
                    long result = ctx.compute(operators.get(i), operandsStack.pop(), Long.parseLong(operands.get(i + 1)));
                    operandsStack.push(result);
                }
            } else {
                if(operandsStack.isEmpty()) {
                    operandsStack.push(Long.parseLong(operands.get(i)));
                    operandsStack.push(Long.parseLong(operands.get(i + 1)));
                } else {
                    operandsStack.push(Long.parseLong(operands.get(i + 1)));
                }
                operatorsStack.push(operators.get(i));
            }
        }
        while(!operatorsStack.isEmpty()) {
            String operator = operatorsStack.pop();
            long rhs = operandsStack.pop();
            long lhs = operandsStack.pop();
            long result = ctx.compute(operator, lhs, rhs);
            operandsStack.push(result);
        }
        return operandsStack.pop();
    }
}
