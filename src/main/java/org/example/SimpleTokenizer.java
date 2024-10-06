package org.example;

import java.util.Arrays;
import java.util.List;

public class SimpleTokenizer implements Tokenizer{
    @Override
    public List<String> tokenize(String text) {
        return Arrays.asList(text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+"));
    }
}
