package com.company.Tokenizer;

import java.util.List;

public class TokensWrapper {
    private List<String> operators;
    private List<String> operands;

    public List<String> getOperators() {
        return operators;
    }

    public List<String> getOperands() {
        return operands;
    }

    public TokensWrapper(List<String> operators, List<String> operands) {
        this.operators = operators;
        this.operands = operands;
    }
}
