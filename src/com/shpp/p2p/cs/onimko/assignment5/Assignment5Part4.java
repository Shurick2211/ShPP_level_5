package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Assignment5Part4 extends TextProgram {

  /**The file name for parsing*/
  private final String FILE_NAME = "New.csv";

  /**The column's index for print*/
  private final int COLUMN = 1;

  /**The separator on the CSV-file */
  private final String CSV_SEPARATOR = ",";

  /**String equals a quotes*/
  private final String ONE_QUOTES = "\"";

  /** Temporary value for changing*/
  private final String TEMP_VALUE = " <@#%> ";

  /**
   * It is start method
   */
  public void run() {
    extractColumn(FILE_NAME, COLUMN).forEach(this::println);

  }

  /**
   * Method returns a column from CSV-file.
   * @param filename the name of CSV-file.
   * @param columnIndex the column's index
   * @return the column as ArrayList
   */
  private ArrayList<String> extractColumn(String filename, int columnIndex) {
    try {
      return (ArrayList<String>) Files.lines(Paths.get(filename))
              .map(str -> fieldsIn(str).get(columnIndex))
              .collect(Collectors.toList());
    } catch (IOException e) {
      return null;
    }
  }

  /**
   * Method creates a row as ArrayList from a string.
   * @param line the input string
   * @return the row as ArrayList
   */
  private ArrayList<String> fieldsIn(String line) {
    String [] fields;
    line = changeValueOnTemp(line);
    // Divide a row into columns
    fields = line.split(TEMP_VALUE);
    rescueValue(fields);
    return (ArrayList<String>) Arrays.stream(fields).collect(Collectors.toList());
  }

  /**
   * Method changes the CSV-separator on the TEMP_VALUE,
   * and writes it of temporary storage.
   * @param line the input string.
   * @return a string with TEMP_VALUE
   */
  private String changeValueOnTemp(String line) {
    int index ;
    while ((index = getSeparatorIndex(line)) != -1)
     line = line.substring(0,index)+TEMP_VALUE+line.substring(index+1);
    return line;
  }

  /**
   * Method rescues the values in the array
   * @param fields the input array with TEMP_VALUE.
   * @return an array with origin values.
   */
  private void rescueValue(String [] fields) {
    for (int i = 0; i < fields.length; i++) {
        fields[i] = removeQuotesInStartAndEnd(fields[i]);
        fields[i] = fields[i].replaceAll(ONE_QUOTES+ONE_QUOTES,ONE_QUOTES);
    }
  }

  /**
   * Method removes quotes in start and end a string,
   * if its need.
   * @param field the input string.
   * @return a string without quotes in start and end.
   */
  private String removeQuotesInStartAndEnd(String field) {
    return field.startsWith(ONE_QUOTES) && field.endsWith(ONE_QUOTES) ?
            field.substring(1,field.length()-1) : field;
  }

  /**
   * This method returns the index of the nearest comma as cell separator.
   * If its none it returns "-1".
   * @param line the input line
   * @return the index of separator or "-1".
   */
  private int getSeparatorIndex(String line) {
    int quotesCounter = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == ONE_QUOTES.charAt(0)) quotesCounter++;
      if (line.charAt(i) == CSV_SEPARATOR.charAt(0) && quotesCounter % 2 == 0)
        return i;
    }
    return -1;
  }
}