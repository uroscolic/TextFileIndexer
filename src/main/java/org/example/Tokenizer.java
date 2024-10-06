package org.example;

import java.util.List;

public interface Tokenizer {

    List<String> tokenize(String text);

    // For different tokenization algorithms, implement this interface and pass it to the TextFileIndexer constructor.
}
