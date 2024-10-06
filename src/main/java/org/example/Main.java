package org.example;

/*
Please write a Java application that provides a service for indexing text files.
The console interface should allow for
(i) specifying the indexed files and directories and
(ii) querying files containing a given word.
The library should be extensible by the tokenization algorithm (simple splitting by words/support lexers/etc.).
State persistence between running sessions is not needed,
but the application should be able to serve consecutive requests.
Providing some tests and usage examples is advised.
Usage of external libraries is discouraged.

The result should be provided as a link to the repository with code on GitHub.
*/


import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TextFileIndexer indexer = new TextFileIndexer(new SimpleTokenizer());
        Set<String> indexedFiles = new HashSet<>();

        while (true) {
            System.out.println("Choose a command: \n" +
                    "1. index files/directories\n" +
                    "2. search\n" +
                    "3. show indexed files\n" +
                    "0. exit\n"
            );
            String command = scanner.nextLine();

            switch (command) {
                case "1":

                    /*
                     * Usage example:
                     *
                     * C:\Users\User\Desktop\file1.txt
                     * C:\Users\User\Desktop\file2.txt
                     * C:\Users\User\Desktop\directory1
                     * exit
                     *
                     * The above example will index files 'file1.txt',
                     * 'file2.txt' and directory 'directory1' from the Desktop directory.
                     *
                     *
                     */

                    System.out.println("Enter paths (Type 'exit' to stop indexing):");
                    while(true) {
                        String path1 = scanner.nextLine();
                        if (path1.equalsIgnoreCase("exit"))
                            break;
                        try {
                            indexer.indexFileOrDirectory(path1);
                            indexedFiles.add(path1);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                case "2":
                    /*
                     * Usage example:
                     *
                     * Enter word to search:
                     * car
                     *
                     * The above example will search for indexed files containing the word 'car'.
                     *
                     *
                     */

                    System.out.println("Enter word to search:");
                    String word = scanner.nextLine();
                    Set<String> result = indexer.search(word);
                    if(result.isEmpty())
                        System.out.println("No files contain '" + word + "'");
                    else {
                        System.out.println("Files containing '" + word + "':");
                        result.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.println("Indexed files and directories:");
                    indexedFiles.forEach(System.out::println);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

}