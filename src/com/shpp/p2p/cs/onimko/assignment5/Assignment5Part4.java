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

  /**
   * It is start method
   */
  public void run() {
    println(extractColumn("food-origins.csv",1));

  }

  /**
   * Method returns a column from CSV-file.
   * @param filename the name of CSV-file.
   * @param columnIndex the column's index
   * @return the column as ArrayList
   */
  private ArrayList<String> extractColumn(String filename, int columnIndex) {
    ArrayList<String> columns = new ArrayList<>();
    ArrayList<String> rows;
    BufferedReader br;
    String str;
    try {
      br = new BufferedReader(new FileReader(filename));
      while ((str=br.readLine()) != null) {
        rows = fieldsIn(str);
        columns.add("\""+rows.get(columnIndex)+"\"");
      }
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
      line = line.replaceAll(",\"", SEPARATOR);
      line = line.replaceAll("\",\"",SEPARATOR);
      line = line.replaceAll("\",",SEPARATOR);
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
    final String SEPARATOR = "\"";
    for (int i = 0; i < fields.length; i++) {
      if (fields[i].contains(SEPARATOR))
        if ( fields[i].split(SEPARATOR).length % 2 == 1
                && fields[i].lastIndexOf(SEPARATOR) == fields[i].length()-1)
          fields[i] = fields[i].substring(0, fields[i].length()-1);
    }
    return fields;
  }


}
