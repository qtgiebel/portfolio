package com.quinngiebel.analyzer.persistence;

import com.quinngiebel.utilities.PropertiesLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class takes a file for input, analyzes each token and prints two reports
 * as output files detailing the distinct tokens and information about the input
 * file.
 * 
 * @author Quinn Giebel
 */
public class FileAnalysis implements PropertiesLoader {
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private List<TokenAnalyzer> analyzers;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This method validates the command line arguments, and calls several sub
     * funcitons to initialize the analyzers, read the input file and write to the
     * output files.
     * 
     * @param args Arguments from the command line.
     */
    public void analyze(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            logger.error("You must enter " + NUMBER_OF_ARGUMENTS + " argument(s) in the command line.");
            return;
        }

        try {
            this.initializeAnalyzers(loadProperties(args[1]));
        } catch (Exception e) {
            logger.error("Failed to load properties.", e);
        }

        this.readFile(args[0]);
        this.writeOutputFiles(args[0]);
    }

    /**
     * This method initializes the token analyzers.
     * 
     * @param properties A preloaded properties object.
     */
    public void initializeAnalyzers(Properties properties) {
        analyzers = new ArrayList<TokenAnalyzer>();

        analyzers.add(new FileSummaryAnalyzer(properties));
        analyzers.add(new DistinctTokensAnalyzer(properties));
        analyzers.add(new LargestTokensAnalyzer(properties));
        analyzers.add(new DistinctTokenCountsAnalyzer(properties));
        analyzers.add(new TokenLengthsAnalyzer(properties));
        analyzers.add(new TokenLocationSearchAnalyzer(properties));
    }

    /**
     * This method opens the input file to read it.
     * 
     * @param fileName The path to the input file.
     */
    public void readFile(String fileName) {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {

            this.parseLines(input);

        } catch (FileNotFoundException fileNotFound) {
            logger.error("No file was not found at: " + System.getProperty("user.dir") + "/" + fileName, fileNotFound);
        } catch (Exception e) {
            logger.error("General exception", e);
        }
    }

    /**
     * This method parses each line of the input file and creates an array of the
     * tokens in that line.
     * 
     * @param input The input reader.
     */
    public void parseLines(BufferedReader input) {
        String line = null;
        String[] tokenBuffer = null;
        try {
            while (input.ready()) {
                line = input.readLine();
                tokenBuffer = line.split("\\W");

                this.parseTokens(tokenBuffer);
            }
        } catch (IOException iException) {
            logger.error("The input file could not be read.", iException);
        } catch (Exception e) {
            logger.error("General Exception", e);
        }
    }

    /**
     * This method parses each token and passes it to the analyzers to be analyzed.
     * 
     * @param tokenBuffer A string of tokens from the current input line.
     */
    public void parseTokens(String[] tokenBuffer) {
        for (String token : tokenBuffer) {
            if (!token.isBlank()) {
                for (TokenAnalyzer analyzer : analyzers) {
                    analyzer.processToken(token);
                }
            }
        }
    }

    /**
     * This method calls on the analyzers to write the output to file.
     * 
     * @param inputFilePath The path to the input file.
     */
    public void writeOutputFiles(String inputFilePath) {
        for (TokenAnalyzer analyzer : analyzers) {
            analyzer.generateOutputFile(inputFilePath);
        }
    }

}