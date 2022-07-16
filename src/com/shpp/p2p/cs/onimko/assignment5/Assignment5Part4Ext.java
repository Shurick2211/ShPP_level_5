package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Assignment5Part4Ext extends TextProgram {

  /**The file name for parsing*/
  private final String FILE_NAME = "New.csv";

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
    printTable(createTable(FILE_NAME));
  }

  /**
   * Method prints a table;
   * @param table the input table;
   */
  private void printTable( ArrayList<ArrayList<String>> table) {
    ArrayList<Integer> sizes = new ArrayList<>();
    for (int j = 0; j < table.size(); j++) {
      sizes.add(table.get(j).stream().map(s->s.length()).reduce(Integer::max).get());
    }
    printLine(sizes);
    for(int i = 0; i < table.get(0).size(); i++){
      for (int j = 0; j < table.size(); j++) {
        print("|");
        print(table.get(j).get(i));
        for (int p = 0; p < sizes.get(j)-table.get(j).get(i).length(); p++) print(" ");
      }
      print("|");
      println("");
      printLine(sizes);
    }
  }

  /**
   * Method creates a table with a file
   * @param fileName the name of input file
   * @return the table
   */
  private ArrayList<ArrayList<String>> createTable(String fileName){
    ArrayList<ArrayList<String>>table = new ArrayList<>();
    int col = 0;
    ArrayList<String> column;
    while ((column = extractColumn(fileName, col++)) != null) {
      table.add(column);
    }
    return table;
  }

  /**
   * Method print a line between rows on the table
   * @param sizes list the maximum size of column
   */
  private void printLine(ArrayList<Integer> sizes) {
    for(int i = 0; i < sizes.size(); i++) {
      print("|");
      for (int ii = 0; ii < sizes.get(i); ii++)
      print("-");
    }
    print("|");
    println("");
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
    } catch (IndexOutOfBoundsException e){
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
    int index ;
    int  endIndex;
    while ((index = line.indexOf(CSV_SEPARATOR+ONE_QUOTES)) != -1
        || (line.indexOf(ONE_QUOTES+CSV_SEPARATOR) != -1)) {
      if(line.startsWith(ONE_QUOTES)) index =-1;
      endIndex = line.indexOf(ONE_QUOTES+CSV_SEPARATOR, index+1)+1;
      if (endIndex < index) endIndex = line.length();
      temp.add(line.substring(index+1 , endIndex));
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
      fields[i] = fields[i].replaceAll(ONE_QUOTES+ONE_QUOTES,ONE_QUOTES);
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