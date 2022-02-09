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
 * This class analyzes each token passed to it to determine if it is unique and
 * prints each to an output file.
 * 
 * @author Quinn Giebel
 */
public class DistinctTokensAnalyzer implements TokenAnalyzer {

    private Set<String> distinctTokens;
    private Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor instantiates the set of distinct tokens.
     */
    public DistinctTokensAnalyzer() {
        this.distinctTokens = new TreeSet<>();
    }

    /**
     * This constructor sets the properties object to retrieve the output directory.
     * 
     * @param properties A preloaded properties object.
     */
    public DistinctTokensAnalyzer(Properties properties) {
        this();// call the empty/no-arg constructor
        this.properties = properties;
    }

    /**
     * This method receives a token and adds it to the set if it it does not already
     * exits.
     * 
     * @param token The token to add to the set.
     */
    @Override
    public void processToken(String token) {
        this.distinctTokens.add(token);
    }

    /**
     * This method opens a file to output the distinct tokens.
     * 
     * @param inputFilePath Path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {
        try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(
                properties.getProperty("output.directory") + properties.getProperty("output.file.distinct"))))) {

            this.printToFile(outputFile);

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.distinct"), oException);
        } catch (Exception e) {
            logger.error("General Exception", e);
        }
    }

    /**
     * This method prints each distinct token to the output file.
     * 
     * @param file The output file to print to.
     */
    public void printToFile(PrintWriter file) {
        for (String token : distinctTokens) {
            file.println(token);
        }
    }

    /**
     * Returns the set of distinct tokens.
     * 
     * @return The set of distinct tokens.
     */
    public Set<String> getDistinctTokens() {
        return distinctTokens;
    }
}