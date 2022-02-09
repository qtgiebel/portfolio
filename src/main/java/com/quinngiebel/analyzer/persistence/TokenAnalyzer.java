package com.quinngiebel.analyzer.persistence;

/**
 * This interface is implemented to process tokens.
 */
public interface TokenAnalyzer {

    /**
     * Processes a token.
     * 
     * @param token The token being processed.
     */
    public void processToken(String token);

    /**
     * This method prints the output of it's analysis to a file.
     * 
     * @param inputFilePath The path to the file that was analyzed.
     */
    public void generateOutputFile(String inputFilePath);
}
