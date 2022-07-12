package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part1 extends TextProgram {

  /**Vowels in the English*/
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
    //get indexes of vowels
    for (int i = 0; i < word.length(); i++)
      for (char vowel : VOWELS)
        if (word.toLowerCase().charAt(i) == vowel) vowelIndexes.add(i);
    return vowelIndexes.size() > 1 ? vowelIndexes.size()-twoLetters(vowelIndexes) : 1;
  }

  /**
   * Method counts vowels in a word, that go one after one
   * @param vowelIndexes list of position vowels in the word
   * @return the number pairs vowels
   */
  private int twoLetters(List <Integer> vowelIndexes){
    int size = vowelIndexes.size();
    int twix = 0;
    for(int i = 0; i < size; i++)
      if(i!= size-1
          && vowelIndexes.get(i).intValue() == vowelIndexes.get(i+1).intValue()-1)
        twix++;
      return twix;
  }
}