package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Assignment5Part3 extends TextProgram {

  /**Vowels in the English*/
  private final String[] VOWELS = {"a","e","i","o","u","y"};
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
            .orElse("No the words of our dictionary.")
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
   * Remove the vowels letters in an input word.
   * @param word the input word.
   * @return the word without vowels letters.
   */
  private String consonantsOfWord(String word) {
    for (String v : VOWELS)
     word = word.replaceAll(v,"");
    return word;
  }

  /**
   * Method checks a word against the content of an input string.
   * @param word the check's word
   * @param str the input string
   * @return true - if the word contains the string or false - if not.
   */
  private boolean checkWord(String word, String str) {
    return  consonantsOfWord(word).contains(str);
  }

  /**
   * Method asks the user an input string.
   * @return the input string.
   */
  private String inputString() {
    String input = readLine("Enter string of three consonants letters: ").toLowerCase();
    if (hasVowels(input)) return null;
    if (input.length() != 3) return null;
    return  input;
  }

  /**
   * Method checks a string against the content of the vowels.
   * @param str the input string.
   * @return true - if the string contains the string or false - if not.
   */
  private boolean hasVowels(String str) {
    for (String v : VOWELS)
      if (str.contains(v)) return true;
    return false;
  }
}
