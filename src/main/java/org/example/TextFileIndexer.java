package org.example;

import java.io.*;
import java.util.*;

public class TextFileIndexer {

    private Map<String, Set<String>> index = new HashMap<>();
    private Tokenizer tokenizer;

    public TextFileIndexer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void indexFileOrDirectory(String path) throws IOException {
        File file = new File(path);
        if (file.isDirectory()) {
            indexDirectory(path);
        } else if(file.isFile()){
            indexFile(path);
        }
        else {
            throw new IllegalArgumentException("File or directory with path: " + path + " not found.");
        }
    }

    public void indexDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null)
            for (File file : files)
                if (file.isFile())
                    indexFile(file.getPath());

    }

    public void indexFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = reader.readLine()) != null){

            List<String> tokens = tokenizer.tokenize(line);
            for (String token : tokens) {
                index.computeIfAbsent(token.toLowerCase(), k -> new HashSet<>()).add(filePath);
            }
        }
    }

    public Set<String> search(String query) {
        Set<String> result = index.get(query.toLowerCase());
        return result == null ? Collections.emptySet() : result;

    }

}
