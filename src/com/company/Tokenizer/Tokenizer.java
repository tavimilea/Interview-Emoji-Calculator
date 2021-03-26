package com.company.Tokenizer;

import java.util.*;

public class Tokenizer {
    public static final Map<String, String> OPERATORS_DICT;
    static {
        OPERATORS_DICT = new HashMap<String, String>();
        OPERATORS_DICT.put("➕", "+");
        OPERATORS_DICT.put("+", "+");
        OPERATORS_DICT.put("plus", "+");
        OPERATORS_DICT.put("minus", "-");
        OPERATORS_DICT.put("➖", "-");
        OPERATORS_DICT.put("-", "-");
        OPERATORS_DICT.put("times", "x");
        OPERATORS_DICT.put("x", "x" );
        OPERATORS_DICT.put("✖", "x");
        OPERATORS_DICT.put("/", "/");
        OPERATORS_DICT.put("%", "/");
        OPERATORS_DICT.put("➗", "/");
        OPERATORS_DICT.put("divided by", "/");

    }
    public static final Map<String, String> OPERANDS_DICT;
    static {
        OPERANDS_DICT = new HashMap<String, String>();
        OPERANDS_DICT.put("\uD83D\uDD1F", "10");
        OPERANDS_DICT.put("\uD83D\uDCAF", "100");
        OPERANDS_DICT.put("\uD83C\uDFB1", "8");
        OPERANDS_DICT.put("0️", "0");
        OPERANDS_DICT.put("1️", "1");
        OPERANDS_DICT.put("2️", "2");
        OPERANDS_DICT.put("3️", "3");
        OPERANDS_DICT.put("4️", "4");
        OPERANDS_DICT.put("5️", "5");
        OPERANDS_DICT.put("6️", "6");
        OPERANDS_DICT.put("7️", "7");
        OPERANDS_DICT.put("8️", "8");
        OPERANDS_DICT.put("9️", "9");
    }
    private TokenRefactor tkRefactor;
    public Tokenizer() {
        this.tkRefactor = new TokenRefactor();
    }

    /**
     * Given a String, it computes and returns "clean tokens" a.k.a
     * tokens representing a math operation, consisting only of numbers(digits 0-9) and accepted
     * math operations(=-*\/)
     * @param input the string to be tokenized
     * @return TokensWrapper object consisting 2 String-arrays, one with operands(numbers) and one with operators
     * @throws Exception if the input string is not valid(usually the user provided wrong input)
     */
    public TokensWrapper tokenize(String input) throws Exception {
        String regex = computeSplitRegexPrFromDict(OPERATORS_DICT);
        TokensWrapper tokens= new TokensWrapper(new LinkedList<String>(Arrays.asList(input.split("[^" + regex +  "]").clone())),
                                                new LinkedList<String>(Arrays.asList(input.split(regex).clone())));
        tokens = tkRefactor.cleanTokens(tokens.getOperators(), tokens.getOperands(), OPERATORS_DICT, OPERANDS_DICT);
        boolean isValid = TokenValidator.getInstance().areTokensValid(tokens, "1234567890", "/*x+-");
        if(!isValid) {
            Exception e = new Exception("The input cannot be parsed");
            throw e;
        }
        return tokens;
    }

    /**
     * Given a Map(alphabet), it computes part o the regex expression needed to separate operands tokens
     * from operators tokens
     * @param symbolsDict a map of Symbols
     * @return for the input ["a":"some_string, "b":"some_string"] the output is "a|b""
     */
    private String computeSplitRegexPrFromDict(Map<String, String> symbolsDict) {
        StringBuilder sb = new StringBuilder();
        Set<String> regexSpecialChars = new HashSet<String>();
        regexSpecialChars.add(".");
        regexSpecialChars.add("$");
        regexSpecialChars.add("|");
        regexSpecialChars.add("(");
        regexSpecialChars.add(")");
        regexSpecialChars.add("[");
        regexSpecialChars.add("{");
        regexSpecialChars.add("^");
        regexSpecialChars.add("?");
        regexSpecialChars.add("*");
        regexSpecialChars.add("+");
        regexSpecialChars.add("%");
        regexSpecialChars.add("-");
        regexSpecialChars.add("\\");
        for(String s : symbolsDict.keySet()) {
            if(regexSpecialChars.contains(s)) {
                sb.append("\\");
            }
            sb.append(s);
            sb.append("|");
        }
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
