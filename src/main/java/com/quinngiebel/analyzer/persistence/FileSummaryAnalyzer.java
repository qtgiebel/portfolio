package com.quinngiebel.analyzer.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * This class analyzes all of the tokens in the input file and prints a report
 * on it.
 * 
 * @author Quinn Giebel
 */
public class FileSummaryAnalyzer implements TokenAnalyzer {
    private int totalTokensCount;
    private Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor initializes the total number of tokens to 0.
     */
    public FileSummaryAnalyzer() {
        totalTokensCount = 0;
    }

    /**
     * This constructor sets the properties object to retrieve the output directory.
     * 
     * @param properties A preloaded properties object.
     */
    public FileSummaryAnalyzer(Properties properties) {
        this();// call the empty/no-arg constructor
        this.properties = properties;
    }

    /**
     * This method increments the total number of tokens for each token in the input
     * file.
     * 
     * @param token The current token being processed.
     */
    @Override
    public void processToken(String token) {
        totalTokensCount++;
    }

    /**
     * Print the path to the input file, the date of the analysis, the last date the
     * file was modified, the size of the file in bytes, the URI and the total
     * number of tokens in the file.
     * 
     * @param inputFilePath The path to the input file.
     */
    @Override
    public void generateOutputFile(String inputFilePath) {

        try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(
                properties.getProperty("output.directory") + properties.getProperty("output.file.summary"))))) {
            File inputFile = new File(inputFilePath);
            Date date = new Date();

            outputFile.println("Application: " + properties.getProperty("application.name"));
            outputFile.println("Author: " + properties.getProperty("author"));
            outputFile.println("Author email: " + properties.getProperty("author.email.address"));
            outputFile.println("File: " + inputFile.getAbsolutePath());
            outputFile.println("Date of analysis: " + date.toString());

            date = new Date(inputFile.lastModified());

            outputFile.println("Last Modified: " + date.toString());
            outputFile.println("File Size: " + inputFile.length());
            outputFile.println("File URI: " + inputFile.toURI());
            outputFile.println("Total Tokens: " + getTotalTokensCount());

        } catch (IOException oException) {
            logger.error("No file could be written to at: " + properties.getProperty("output.directory")
                    + properties.getProperty("output.file.summary"), oException);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method returns the total number of tokens.
     * 
     * @return The total number of tokens.
     */
    public int getTotalTokensCount() {
        return totalTokensCount;
    }

}