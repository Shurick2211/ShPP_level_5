package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Assignment5Part3 extends TextProgram {

  /** Our dictionary*/
  private final List<String> words = readDictionary();

  /**
   * It is start method
   */
  public void run() {
    while (true) {
      String str = null;
      while (str == null) str = inputString();
      printWords(str);
    }
  }

  /**
   * Method prints the finding words for an input string.
   * @param str the input str
   */
  private void printWords(String str) {
    println(
        words.stream().filter(word -> checkWord(word, str))
            .reduce((w,ww) -> w+", " + ww  )
            .orElse("The our dictionary has no the words for your string.")
    );
  }

  /**
   * Method reads the dictionary from a file.
   * @return LinkedList with word or prints error
   * and ends program.
   */
  private List<String> readDictionary()  {
    List<String> words = new LinkedList<>();
    BufferedReader br;
    String str;
    try {
      br = new BufferedReader(new FileReader("en-dictionary.txt"));
      while ((str=br.readLine()) != null)
      words.add(str);
    } catch (FileNotFoundException e) {
      println(e.getMessage());
      System.exit(1);
    } catch (IOException e) {
      println(e.getMessage());
      System.exit(2);
    }
    return words;
  }

  /**
   * Method checks a word against the content of an input string.
   * @param word the check's word
   * @param str the input string
   * @return true - if the word contains the string or false - if not.
   */
  private boolean checkWord(String word, String str) {
    int first = word.indexOf(str.charAt(0));
    int second = word.indexOf(str.charAt(1));
    int third = word.indexOf(str.charAt(2));
    return  first > -1 && second > first && third > second;
  }

  /**
   * Method asks the user an input string.
   * @return the input string.
   */
  private String inputString() {
    String input = readLine("Enter string of three letters: ").toLowerCase();
    if (input.length() != 3) return null;
    return  input;
  }
}