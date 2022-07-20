/**
 * File: Assignment5Part4.java
 * ---------------------------
 * This program extracts any column of *.csv file
 */
package com.shpp.p2p.cs.amoiseienko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class extracts a column of any well formatted *.csv file
 */
public class Assignment5Part4 extends TextProgram {

    /* *.csv file path */
    private static final String FILE_NAME = "new.csv";

    /* Index of column to extract */
    private static final int COLUMN_INDEX =1;

    /**
     * This method shows in console log a column of *.csv file if it's not null
     */
    public void run() {
        ArrayList<String> column = extractColumn(FILE_NAME, COLUMN_INDEX);
        if (column != null) {
            println("Column entry: ");
            for (String entry : column) {
                println("\t\t\t  " + entry);
            }
        }
    }

    /**
     * This method extracts a column of *.csv file and returns it as array.
     * If column or file does not exist it returns null
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {

        /* Getting file entry as an string array */
        ArrayList<String> fileEntry = readFile(filename);

        /* Checking of null file entry and column index isn't out of borders */
        if (fileEntry == null) return null;
        if (columnIndex > fieldsIn(fileEntry.get(0)).size() - 1) {
            println("This column does not exist.");
            return null;
        }

        ArrayList<String> currentColumn = new ArrayList<>();

        /* Adding column to a array bu fieldsIn() method */
        for (String line : fileEntry) {
            currentColumn.add(fieldsIn(line).get(columnIndex));
        }
        return currentColumn;
    }

    /**
     * This method receiving one row of *.csv file and returns it as array of cells.
     */
    private ArrayList<String> fieldsIn(String line) {

        ArrayList<String> result = new ArrayList<>();

        while (getSeparatorIndex(line) != -1) {
            /* While separators is found adding cells entry to result array */
            result.add(clearQuotes(line.substring(0, getSeparatorIndex(line))));
            if (getSeparatorIndex(line) != line.length() - 1) {
                /* If separator isn't last line index - cutting added cell from a line */
                line = line.substring(getSeparatorIndex(line) + 1);
            } else {
                /* If separator it is the last line index - adding empty cell. The line is over. */
                result.add("");
                line = "";
            }
        }
        /* Adding the entry of last cell if it isn't empty */
        if (line.length() > 0) {
            result.add(clearQuotes(line));
        }
        return result;
    }

    /**
     * This method returns the index of the nearest comma as cell separator.
     * If its none it returns "-1".
     */
    private int getSeparatorIndex(String line) {
        int quotesCounter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                quotesCounter++;
            }
            if (line.charAt(i) == ',' && quotesCounter % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method clearing extra quotes in one *.csv file cell
     * and returns entry of cell.
     */
    private String clearQuotes(String line) {
        if (line.startsWith("\"")) {
            line = line.substring(1, line.length() - 1);
            line = line.replaceAll("\"\"", "\"");
        }
        return line;
    }

    /**
     * This method reading a  file and returns it as array of rows.
     * If file does not exist it returns null.
     */
    private ArrayList<String> readFile(String fileName) {

        ArrayList<String> fileEntry = new ArrayList<>();

        try {
            /* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            /* Processing the file by adding one line after another. */
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    fileEntry.add(line);
                }
            }
            br.close();

            /* If something goes wrong returning null */
        } catch (IOException e) {
            println("No such file or directory.");
            return null;
        }
        return fileEntry;
    }
}
