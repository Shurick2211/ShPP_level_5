package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Assignment5Part4 extends TextProgram {

  /**The file name for parsing*/
  private final String FILE_NAME = "food-origins.csv";

  /**The column's index for print*/
  private final int COLUMN = 1;

  /**The file name for parsing*/
  private final String[] SEPARATORS = {",\"", "\",\"", "\","};

  /**The file name for parsing*/
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
    ArrayList<String> columns = new ArrayList<>();
    String str;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while ((str=br.readLine()) != null)
        columns.add(ONE_QUOTES + fieldsIn(str).get(columnIndex) + ONE_QUOTES);
    } catch (FileNotFoundException e) {
      println(e.getMessage());
      return null;
    } catch (IOException e) {
      println(e.getMessage());
      System.exit(1);
    }
    return columns;
  }

  /**
   * Method creates a row as ArrayList from a string.
   * @param line the input string
   * @return the row as ArrayList
   */
  private ArrayList<String> fieldsIn(String line) {
    final String SEPARATOR = " <@#%> ";
    String [] fields;
    if (!line.contains(",\"")) fields = line.split(",");
    else {
      for (String sep : SEPARATORS) line = line.replaceAll(sep, SEPARATOR);
      fields = line.split(SEPARATOR);
      fields = removeLastQuotes(fields);
    }
    return (ArrayList<String>) Arrays.stream(fields).collect(Collectors.toList());
  }

  /**
   * Method removes a last extra quotes from an array
   * @param fields the input array
   * @return the array without extra quotes
   */
  private String[] removeLastQuotes(String[] fields) {
    for (int i = 0; i < fields.length; i++) {
      if (fields[i].contains(ONE_QUOTES))
        if (fields[i].split(ONE_QUOTES).length % 2 == 1
                && fields[i].lastIndexOf(ONE_QUOTES) == fields[i].length()-1)
          fields[i] = fields[i].substring(0, fields[i].length()-1);
    }
    return fields;
  }
}