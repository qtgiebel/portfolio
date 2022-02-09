package com.quinngiebel.analyzer.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * This class cross references each token processed with the tokens in a
 * separately specified list storing the location of each occurence of any such
 * specified tokens.
 * 
 * @author Quinn Giebel
 */
public class TokenLocationSearchAnalyzer implements TokenAnalyzer {
    private static final int MAX_LENGTH = 80;

    private Map<String, List<Integer>> foundLocations;
    private Properties properties;
    private int currentTokenLocation;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor instantiates the map of the tokens and their respective
     * locations and initializes the current location to 1.
     */
    public TokenLocationSearchAnalyzer() {
        foundLocations = new TreeMap<String, List<Integer>>();
        currentTokenLocation = 1;
    }

    /**
     * This constructor sets the properties object to retrieve the output directory,
     * as well as retrieving the list of tokens to search for.
     * 
     * @param properties A properties object that has already been loaded.
     */
    public TokenLocationSearchAnalyzer(Properties properties) {
        this();
        this.properties = properties;
        this.populateMap();
    }

    /**
     * This method retrieves the list of tokens to search for.
     */
    private void populateMap() {
        try (InputStream inputStream = this.getClass()
                .getResourceAsStream(properties.getProperty("classpath.search.tokens"));
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader searchTokensReader = new BufferedReader(inputStreamReader)) {
            this.readSearchTokens(searchTokensReader);
        } catch (IOException inputOutputException) {
            logger.error("No file could be found at: " + properties.getProperty("classpath.search.tokens"), inputOutputException);
        } catch (Exception exception) {
            logger.error("General exception", exception);
        }
    }

    /**
     * This method reads through the list of search tokens and adds each to the map
     * of tokens with an empty list as the value.
     * 
     * @param searchTokensReader A buffer containing the tokens to search for.
     */
    private void readSearchTokens(BufferedReader searchTokensReader) {
        try {
            String key;

            while (searchTokensReader.ready()) {
                key = searchTokensReader.readLine().replaceAll("\\s+", "");
                if (!key.isBlank()) {
                    foundLocations.put(key, new ArrayList<Integer>());
                }
            }
        } catch (IOException e) {
            logger.error("Could not read from input.", e);
        }
    }

    /**
     * This method checks if the current token is present in the list to be searched
     * for and adds it to that tokens list of locations.
     * 
     * @param token The current token being processed.
     */
    @Override
    public void processToken(String token) {
        if (foundLocations.containsKey(token)) {
            List<Integer> locations = foundLocations.get(token);
            locations.add(currentTokenLocation);
            foundLocations.put(token, locations);
        }

        currentTokenLocation++;
    }

    /**
     * This method generates the output file.
     * 
     * @param inputFilePath The path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {
        try (PrintWriter outputFile = new PrintWriter(
                new BufferedWriter(new FileWriter(properties.getProperty("output.directory")
                        + properties.getProperty("output.file.token.search.locations"))))) {

            this.printToFile(outputFile);

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.token.search.locations"), oException);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method retrieves each key:value pair from the map, formats them and
     * print them to the file.
     * 
     * @param file The output file being written to.
     */
    public void printToFile(PrintWriter file) {
        List<Integer> list;

        for (Map.Entry<String, List<Integer>> entry : this.foundLocations.entrySet()) {
            file.println(entry.getKey() + " =");
            list = entry.getValue();

            if (list.isEmpty()) {
                file.println("[]");
            } else {
                this.printTokens(file, list);
            }

            file.print("\n");
        }
    }

    /**
     * This method ensures that each line in the output file is no more than 80
     * characters long.
     * 
     * @param file The output file being written to.
     * @param list The list of locations for the current token being printed.
     */
    private void printTokens(PrintWriter file, List<Integer> list) {
        String out = "[";

        while (!list.isEmpty()) {
            if (list.size() == 1) {
                out += list.get(0) + "]";
                file.println(out);
                break;
            } else {
                out += list.get(0) + ", ";
            }

            list.remove(0);

            if (out.length() + (list.get(0) + ",").length() > MAX_LENGTH) {
                out = out.substring(0, out.length() - 1);
                file.println(out);
                out = "";
            }
        }
    }

    /**
     * This method return the value of foundLocations.
     * 
     * @return The map of search tokens and found locations.
     */
    public Map<String, List<Integer>> getFoundLocations() {
        return foundLocations;
    }
}