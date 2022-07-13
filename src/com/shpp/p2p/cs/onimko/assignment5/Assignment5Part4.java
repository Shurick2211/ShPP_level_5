package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Assignment5Part4 extends TextProgram {

  /**The file name for parsing*/
  private final String FILE_NAME = "food-origins.csv";

  /**The column's index for print*/
  private final int COLUMN = 1;

  /**The separator on the CSV-file */
  private final String CSV_SEPARATOR = ",";

  /**String equals a quotes*/
  private final String ONE_QUOTES = "\"";

  /** Temporary value for changing*/
  private final String TEMP_VALUE = " <@#%> ";

  /**Storage for temporary strings*/
  private final Deque<String> temp = new LinkedList<>();

  /**
   * It is start method
   */
  public void run() {
    println(extractColumn(FILE_NAME, COLUMN));

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
              .map(str -> ONE_QUOTES + fieldsIn(str).get(columnIndex) + ONE_QUOTES)
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
    fields = line.split(CSV_SEPARATOR);
    return (ArrayList<String>) Arrays.stream(rescueValue(fields)).collect(Collectors.toList());
  }

  /**
   * Method changes the values of the columns like "..." to TEMP_VALUE,
   * and writes it of temporary storage.
   * @param line the input string.
   * @return a string with TEMP_VALUE
   */
  private String changeValueOnTemp(String line) {
    int index;
    while ((index = line.indexOf(ONE_QUOTES)) != -1) {
      temp.add( line.substring(index , line.indexOf(ONE_QUOTES, index + 1) + 1));
      line = line.replace(temp.peekLast(), TEMP_VALUE);
    }
    return line;
  }

  /**
   * Method rescues the values in the array, that contains the TEMP_VALUE,
   * used for its temporary storage.
   * @param fields the input array with TEMP_VALUE.
   * @return an array with origin values.
   */
  private String [] rescueValue(String [] fields) {
    int index;
    for (int i = 0; i < fields.length; i++) {
      while ((index = fields[i].indexOf(TEMP_VALUE)) != -1)
        fields[i] = fields[i].substring(0, index)
                + temp.pollFirst() + fields[i].substring(index+TEMP_VALUE.length());
        fields[i] = removeQuotesInStartAndEnd(fields[i]);
    }
    return fields;
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
}