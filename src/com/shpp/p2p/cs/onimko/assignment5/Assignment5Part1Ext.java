package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part1Ext extends TextProgram {

  /** Array of vowels*/
  private char[] vowels;

  /**Vowels in the English*/
  private final char[] EN_VOWELS = {'a','e','i','o','u','y'};

  /**Vowels in the Cyrillic*/
  private final char[] CYRILLIC_VOWELS =
      {'а', 'е', 'и', 'і', 'о', 'у', 'я', 'ю', 'є', 'ї', 'э', 'ы', 'ё'};

  /**
   * It is start method
   */
  public void run() {
    /* Repeatedly prompt the user for a word and print out the estimated
     * number of syllables in that word.
     */
    while (true) {
      String word = readLine("Enter a single word: ");
      if (isEnglish(word)) vowels = EN_VOWELS;
      else vowels = CYRILLIC_VOWELS;
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
    int addition = additionSyllable(word);
    List <Integer> vowelIndexes = new ArrayList<>();
    word = cutLastE(word);
    //get indexes of vowels
    for (int i = 0; i < word.length(); i++)
      for (char vowel : vowels)
        if (word.toLowerCase().charAt(i) == vowel) vowelIndexes.add(i);
    return vowelIndexes.size() == 0 ? 1 :
        vowelIndexes.size() - twoLetters(vowelIndexes) + addition;
  }

  /**
   * Method counts vowels in a word, that go one after one
   * @param vowelIndexes list of position vowels in the word
   * @return the number pairs vowels
   */
  private int twoLetters(List <Integer> vowelIndexes){
    if (vowels.equals(CYRILLIC_VOWELS)) return 0;
    int size = vowelIndexes.size();
    int twix = 0;
    for(int i = 0; i < size; i++)
      if(i!= size-1
          && vowelIndexes.get(i).intValue() == vowelIndexes.get(i+1).intValue()-1)
        twix++;
      return twix;
  }

  /**
   * Method cuts "e" of the end of a word, if its need.
   * @param word the input word
   * @return the word without e of the end.
   */
  private String cutLastE(String word) {
    return (word.toLowerCase().endsWith("e")) ?  word.substring(0,word.length()-1) : word;
  }

  /**
   * Method adds an addition syllable, if its need.
   * @param word the input word
   * @return 0 or 1
   */
  private int additionSyllable(String word) {
    String [] endAdditions = {"le","nd","ld"};
    for (String sy : endAdditions)
      if (word.endsWith(sy)) return 1;
    return 0;
  }

  /**
   *
   * @param world the input world
   * @return true - language is English,
   * false - other;
   */
  private boolean isEnglish(String world) {
    int value = world.toLowerCase().charAt(0);
    int lastEn = 'z';
    if (value <= lastEn) return true;
    return false;
  }
}