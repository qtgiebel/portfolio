package com.quinngiebel.analyzer.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class analyzes the length of each token processed and outputs the length
 * of the tokens with the number of tokens of that length, as well as a
 * histogram showing the same data.
 * 
 * @author Quinn Giebel
 */
public class TokenLengthsAnalyzer implements TokenAnalyzer {
    private static final int MAX_HISTOGRAM_BAR = 75;
    private Map<Integer, Integer> tokenLengths;
    private Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor instantiates the map of the tokens and their respective
     * counts.
     */
    public TokenLengthsAnalyzer() {
        tokenLengths = new TreeMap<Integer, Integer>();
    }

    /**
     * This constructor sets the properties object to retrieve the output directory.
     * 
     * @param properties A properties object that has already been loaded.
     */
    public TokenLengthsAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    /**
     * This method takes a token and checks if there are any other tokens of the
     * same length in the map. If there are not adds a new entry to the map, else
     * updates the value of the current length.
     * 
     * @param token The token being processed.
     */
    @Override
    public void processToken(String token) {
        Integer length = token.length();

        if (tokenLengths.containsKey(length)) {
            tokenLengths.put(length, tokenLengths.get(length) + 1);
        } else {
            tokenLengths.put(length, 1);
        }
    }

    /**
     * This method generates the output file.
     * 
     * @param inputFilePath The path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {
        try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(
                properties.getProperty("output.directory") + properties.getProperty("output.file.token.lengths"))))) {

            this.printToFile(outputFile);

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.token.lengths"), oException);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method prints the formatted map and a histogram to visualize the data.
     * 
     * @param file The file being printed to.
     */
    public void printToFile(PrintWriter file) {
        Set<Entry<Integer, Integer>> tokens = this.tokenLengths.entrySet();
        file.write(this.printCounts(tokens) + "\n");
        file.write(this.printHistogram(tokens));
    }

    /**
     * This method formats each key:value pair in the map onto an individual line.
     * 
     * @param tokens A set view of the lengths of the tokens.
     * @return The fully formatted list of the lengths.
     */
    private String printCounts(Set<Entry<Integer, Integer>> tokens) {
        String countString = "";

        for (Entry<Integer, Integer> entry : tokens) {
            countString += entry.getKey() + "\t" + entry.getValue() + "\n";
        }

        return countString;
    }

    /**
     * This method generates a histogram to visualize the quantity of the tokens
     * based on length.
     * 
     * @param tokens A set view of the lengths of the tokens.
     * @return The fully formatted histogram of the lengths.
     */
    private String printHistogram(Set<Entry<Integer, Integer>> tokens) {
        String histogram = "";
        int maxValue = this.getMaxValue(tokens);
        int displayValue;

        for (Entry<Integer, Integer> entry : tokens) {
            displayValue = (entry.getValue() * MAX_HISTOGRAM_BAR) / maxValue;

            histogram += entry.getKey() + "\t*";

            for (int i = 0; i < displayValue; i++) {
                histogram += "*";
            }

            histogram += "\n";
        }

        return histogram;
    }

    /**
     * This method retrieves the largest quantity of tokens at a specific lengths.
     * 
     * @param tokens A set view of the lengths of the tokens.
     * @return The largest quantity of tokens.
     */
    private int getMaxValue(Set<Entry<Integer, Integer>> tokens) {
        Entry<Integer, Integer> maxValue = null;
        for (Entry<Integer, Integer> entry : tokens) {
            if (maxValue == null || entry.getValue().compareTo(maxValue.getValue()) > 0) {
                maxValue = entry;
            }
        }

        return maxValue.getValue();
    }

    /**
     * This method return the value of tokenLength.
     * 
     * @return The map of token lengths and quantities.
     */
    public Map<Integer, Integer> getTokenLengths() {
        return tokenLengths;
    }
}