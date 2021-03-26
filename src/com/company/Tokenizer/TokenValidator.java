package com.company.Tokenizer;

import java.util.List;

public class TokenValidator {
    private static TokenValidator instance = null;

    private TokenValidator(){}

    public static TokenValidator getInstance() {
        if(instance == null) {
            instance = new TokenValidator();
        }
        return instance;
    }

    /**
     * Given some tokens, it checks if the math operation they compose
     * is mathematically valid. Constraints checked(in this order):
     *  1.No operands is empty, no operand is " "
     *  2.Operators count = Operands count - 1;
     *  3.Operands are constructed only from symbols to be found in param operandsVocab
     *  4 Operators are constructed only from symbols to be found in param operatorsVocab
     * @param tokens TokensWrapper to be checked
     * @param operandsVocab String containing symbols allowed in a math-expression operand
     * @param operatorsVocab String containing symbols allowed in a math-expression operator
     * @return true if all tokens are valid, false otherwise
     */
    public boolean areTokensValid(TokensWrapper tokens,
                                  String operandsVocab,
                                  String operatorsVocab) {
        long invalidOperandCnt  = tokens.getOperands().stream()
                                    .filter(op -> op.length() == 0 || op.contains(" "))
                                        .count();

        int operatorsSz = tokens.getOperands().size();
        int operandsSz = tokens.getOperands().size();
        if((tokens.getOperands().size() - tokens.getOperators().size() != 1)
                || (invalidOperandCnt != 0)) {
            return false;
        }
        boolean isMatchingOperands = checkIndividualTokens(tokens.getOperands(), operandsVocab);
        boolean isMatchingOperators = checkIndividualTokens(tokens.getOperators(), operatorsVocab);
        return isMatchingOperands && isMatchingOperators;
    }

    /**
     * Given a list of strings it checks if every member of the string is formed
     * only from chars present in the vocabulary.
     * @param tokens List of strings to be checked
     * @param vocab A string containing all of the allowed chars
     * @return
     */
    private boolean checkIndividualTokens(List<String> tokens, String vocab) {
        for(String s : tokens) {
         if(!s.matches("^["+vocab+".]+")) {
             return false;
         }
        }
        return true;
    }
}
