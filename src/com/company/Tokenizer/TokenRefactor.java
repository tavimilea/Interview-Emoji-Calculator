package com.company.Tokenizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TokenRefactor {

    /**
     * Clears tokens(Strings) by applying multiple edits in the following order:
     *  1. remove whitespace padding at the left&right of the tokens
     *  2. removes extra-tokens which resulted as trash from the initial splitting
     *  3. replaces special chars in each token 4️2️+🎱
     * Example: for the input operands = ["4️2️", "🎱"], operators = ["➕"] output => operands = ["42", "8"], operators = ["+"]
     * @param operators operators tokens
     * @param operands operands tokens
     * @param operatorsDict operators dictionary
     * @param numbersDict operands dictionary
     * @return New TokensWrapper, containing only clean tokens
     */
    public TokensWrapper cleanTokens(List<String> operators,
                                     List<String> operands,
                                     Map<String, String> operatorsDict,
                                     Map<String, String> numbersDict) {
        List<String> opts = new LinkedList<String>(operators);
        List<String> oprds = new LinkedList<String>(operands);

        oprds = clearWhiteSpacePadding(oprds);
        opts = clearWhiteSpacePadding(opts);

        opts.removeIf(op -> op.equals("") || op.equals("\uFE0F"));

        oprds = replaceSpecialChars(oprds, numbersDict);
        opts = replaceSpecialChars(opts, operatorsDict);

        return new TokensWrapper(opts, oprds);
    }

    /**
     * Trim every string inside of a list
     * @param input List of Strings to be trimmed
     * @return new List of Strings containing trimmed strings
     */
    private List<String> clearWhiteSpacePadding(List<String> input) {
        List<String> mutableStrings = new LinkedList<String>(input);
        for(int i = 0; i < mutableStrings.size(); i ++) {
            mutableStrings.set(i, mutableStrings.get(i).trim());
        }
        return mutableStrings;
    }

    /**
     * Replaces every string chars which are equal to a key from
     * replacementDict with the value replacementKey["key"].
     * @param input List of Strings
     * @param replacementDict replacement Map where key = value to be searched in string,
     *                          value = value to replace the occurrences of
     *                          the respective key
     * @return new List containing replaced strings
     */
    private List<String> replaceSpecialChars(List<String> input,
                                             Map<String, String> replacementDict) {
        List<String> mutableStrings = new LinkedList<>(input);
        for(int i = 0; i < mutableStrings.size(); i ++) {
            for(String key : replacementDict.keySet()) {
                    mutableStrings.set(i,
                            mutableStrings.get(i)
                                    .replace(key, replacementDict.get(key)));
            }
            mutableStrings.set(i,
                    mutableStrings.get(i)
                            .replace("\uFE0F", ""));
        }
        return mutableStrings;
    }
}
