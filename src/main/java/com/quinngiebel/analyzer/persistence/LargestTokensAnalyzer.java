package com.quinngiebel.analyzer.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class analyzes each token passed to it to determine if it is large and
 * prints each large token to an output file.
 * 
 * @author Quinn Giebel
 */
public class LargestTokensAnalyzer implements TokenAnalyzer {

    private Set<String> largestTokens;
    private Properties properties;
    private int minimumTokenLength;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor instantiates the set of large tokens.
     */
    public LargestTokensAnalyzer() {
        this.largestTokens = new TreeSet<>();
    }

    /**
     * This constructor sets the properties object to retrieve the output directory.
     * 
     * @param properties A preloaded properties object.
     */
    public LargestTokensAnalyzer(Properties properties) {
        this();
        this.properties = properties;
        this.minimumTokenLength = Integer.parseInt(properties.getProperty("largest.words.minimum.length"));
    }

    /**
     * This method receives a token and adds it to the set if it does not already
     * exits.
     * 
     * @param token The token to add to the set.
     */
    @Override
    public void processToken(String token) {
        if (token.length() >= minimumTokenLength) {
            this.largestTokens.add(token);
        }
    }

    /**
     * This method opens a file to output the large tokens.
     * 
     * @param inputFilePath Path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {
        try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(
                properties.getProperty("output.directory") + properties.getProperty("output.file.largest.words"))))) {

            this.printToFile(outputFile);

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.largest.words"), oException);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method prints each large token to the output file.
     * 
     * @param file The output file to print to.
     */
    public void printToFile(PrintWriter file) {
        for (String token : largestTokens) {
            file.println(token);
        }
    }

    /**
     * Returns the set of large tokens.
     * 
     * @return The set of large tokens.
     */
    public Set<String> getLargestTokens() {
        return largestTokens;
    }
}