package com.company;


import com.company.Calculator.ExpressionSolver;
import com.company.EmojiNumberGenerator.EmojiNumberGenerator;
import com.company.Tokenizer.Tokenizer;
import com.company.Tokenizer.TokensWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String inputOperation = reader.readLine();
        reader.close();
        Tokenizer tk = new Tokenizer();
        EmojiNumberGenerator emjNmbGen = new EmojiNumberGenerator();
        try {
            TokensWrapper tokens =  tk.tokenize(inputOperation);
            ExpressionSolver solver = new ExpressionSolver();
            try {
                long result = solver.solveFor(tokens.getOperators(), tokens.getOperands());
                System.out.println(emjNmbGen.generateEmojiNumber(result));
            } catch(Exception e) {
                System.out.println("\uD83E\uDD37");
            }
        } catch(Exception e) {
           System.out.println("Could not determine the input, please check your input for errors.");
        }

    }
}
