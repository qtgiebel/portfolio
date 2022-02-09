package com.quinngiebel.analyzer.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * This class stores each distinct token and how many times it appeared in the
 * input file.
 * 
 * @author Quinn Giebel
 */
public class DistinctTokenCountsAnalyzer implements TokenAnalyzer {

    private Properties properties;
    private Map<String, Integer> distinctTokenCounts;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor instantiates the map of the lengths of the tokens and the
     * count of each.
     */
    public DistinctTokenCountsAnalyzer() {
        distinctTokenCounts = new TreeMap<String, Integer>();
    }

    /**
     * This constructor sets the properties object to retrieve the output directory.
     * 
     * @param properties A properties object that has already been loaded.
     */
    public DistinctTokenCountsAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    /**
     * This method processes a token. If it is not already present in the map, it is
     * put. Otherwise the value associated with the token is incremented.
     * 
     * @param token The current token being processed.
     */
    @Override
    public void processToken(String token) {
        Integer value = distinctTokenCounts.putIfAbsent(token, 1);

        if (value != null) {
            distinctTokenCounts.put(token, value + 1);
        }
    }

    /**
     * This method opens a file to output the distinct tokens.
     * 
     * @param inputFilePath Path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {
        try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(
                properties.getProperty("output.directory") + properties.getProperty("output.file.distinct.counts"))))) {

            this.printToFile(outputFile);

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.distinct.counts"), oException);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method prints each distinct token to the output file.
     * 
     * @param file The output file to print to.
     */
    public void printToFile(PrintWriter file) {

        for (Map.Entry<String, Integer> entry : distinctTokenCounts.entrySet()) {
            file.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    /**
     * Returns the Map of tokens and counts.
     * 
     * @return The map of tokens and values.
     */
    public Map<String, Integer> getDistinctTokenCounts() {
        return distinctTokenCounts;
    }
}
