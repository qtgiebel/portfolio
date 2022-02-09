package com.quinngiebel.analyzer.persistence;

/**
 * This class is a text class that runs the analyzer.
 * 
 * @author Quinn Giebel
 */
public class Driver {

    /**
     * Creates an instance of the analyzer and runs it.
     * 
     * @param args Arguments from the command line.
     */
    public static void main(String[] args) {
        FileAnalysis analyzer = new FileAnalysis();
        analyzer.analyze(args);
    }

}