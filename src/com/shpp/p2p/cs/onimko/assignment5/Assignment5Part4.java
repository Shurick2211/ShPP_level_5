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
    final String TEMP_VALUE = " <@#%> ";
    String [] fields;
    Deque<String> temp = new LinkedList<>();
    int index;
    // Change the values of the columns like "..." to TEMP_VALUE, and writes it of temp
    while ((index = line.indexOf(ONE_QUOTES))!= -1) {
      temp.add( line.substring(index , line.indexOf(ONE_QUOTES, index + 1)+1));
      line = line.replace(temp.peekLast(), TEMP_VALUE);
    }
    // Divide a row into columns
    fields = line.split(CSV_SEPARATOR);
    // Change back
    for (int i = 0; i < fields.length; i++) {
      if ((index = fields[i].indexOf(TEMP_VALUE)) != -1)
        if (index == fields[i].lastIndexOf(TEMP_VALUE)) fields[i] = fields[i].replace(TEMP_VALUE,temp.pollFirst());
        else while ((index = fields[i].indexOf(TEMP_VALUE)) != -1 )
          fields[i] = fields[i].substring(0, index)
                  + temp.pollFirst() + fields[i].substring(index+TEMP_VALUE.length());
      if (fields[i].startsWith(ONE_QUOTES)) fields[i] = fields[i].substring(1,fields[i].length()-1);
    }
    return (ArrayList<String>) Arrays.stream(fields).collect(Collectors.toList());
  }
}