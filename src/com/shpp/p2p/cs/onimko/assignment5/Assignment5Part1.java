package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part1 extends TextProgram {

  /**Vowels in the language*/
  private final char[] VOWELS = {'a','e','i','o','u','y'};

  /**
   * It is start method
   */
  public void run() {
    /* Repeatedly prompt the user for a word and print out the estimated
     * number of syllables in that word.
     */
    while (true) {
      String word = readLine("Enter a single word: ");
      println("  Syllable count: " + syllablesIn(word));
    }
  }

  /**
   * Given a word, estimates the number of syllables in that word according to the
   * heuristic specified in the handout.
   *
   * @param word A string containing a single word.
   * @return An estimate of the number of syllables in that word.
   */
  private int syllablesIn(String word) {
    List <Integer> vowelIndexes = new ArrayList<>();
    //cut "e" of the end
    if (word.toLowerCase().endsWith("e")) word = word.substring(0,word.length()-1);
    //String to char array
    char[] chars = word.toLowerCase().toCharArray();
    //get indexes of vowels
    for (int i = 0; i < chars.length; i++)
      for (char vowel : VOWELS)
        if (chars[i] == vowel)
              vowelIndexes.add(i);
    // number of vowels
    int size = vowelIndexes.size();
    // count vowels, that go one after one
    int twix = 0;
    for(int i = 0; i < size; i++)
      if(i!= size-1
          && vowelIndexes.get(i).intValue() == vowelIndexes.get(i+1).intValue()-1)
        twix++;
    return size > 1 ? size-twix : 1;
  }
}