package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5Part3Ext extends TextProgram {

  /** File name our dictionary*/
  private final String FILE = "en-dictionary.txt";

  private final List<String> myDict = readDictionary();

  /**
   * It is start method
   */
  public void run() {
    boolean first = true;
    int start = 'a';
    int end = 'z';
    int size = end - start;
    String str;
      for (int f = 0; f <= size; f++)
        for (int s = 0; s <= size; s++)
          for (int t = 0; t <= size; t++) {
            str = String.valueOf(new char[]
                {(char) (f + 'a'), (char) (s + 'a'), (char) (t + 'a')});
            if(checksNoWords(str))
              if (first) {
                print(str);
                first = false;
              } else print(", "+str);
      }
  }

  /**
   * Method checks the finding no words for an input string.
   * @param str the input string
   */
  private boolean checksNoWords(String str) {
        return myDict.stream().parallel()
            .filter(word -> checkWord(word, str)).count() == 0;
  }

  /**
   * Method reads the dictionary from a file.
   * @return LinkedList with word or prints error
   * and ends program.
   */
  private List<String> readDictionary()  {
    try {
      return Files.lines(Paths.get(FILE)).parallel().collect(Collectors.toList());
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
    int second = word.indexOf(str.charAt(1),first + 1);
    int third = word.indexOf(str.charAt(2), second + 1);
    return  first != -1 && second !=-1 && third !=-1;
  }

}