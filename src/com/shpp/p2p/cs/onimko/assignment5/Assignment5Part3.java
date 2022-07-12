package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5Part3 extends TextProgram {

  /** File name our dictionary*/
  private final String FILE = "en-dictionary.txt";

  /**
   * It is start method
   */
  public void run() {
      String str;
      if ((str = inputString()) != null) printWords(str);
      run();
  }

  /**
   * Method prints the finding words for an input string.
   * @param str the input str
   */
  private void printWords(String str) {
    println(
        readDictionary().stream().filter(word -> checkWord(word, str))
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
    try {
      return Files.lines(Paths.get(FILE)).collect(Collectors.toList());
    } catch (IOException e) {
      println(e.toString());
      System.exit(2);
    }
    return null;
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
    String input;
    return  (input = readLine("Enter string of three letters: ").toLowerCase()).length() == 3 ? input: null;
  }
}