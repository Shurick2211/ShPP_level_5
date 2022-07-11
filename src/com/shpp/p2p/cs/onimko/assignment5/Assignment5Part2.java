package com.shpp.p2p.cs.onimko.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

  /**
   * It is start method
   */
  public void run() {
    /* Sit in a loop, reading numbers and adding them. */
    while (true) {
      String n1 = readLine("Enter first number:  ");
      String n2 = readLine("Enter second number: ");
      println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
      println();
    }
  }

  /**
   * Given two string representations of nonnegative integers, adds the
   * numbers represented by those strings and returns the result.
   * @param n1 The first number.
   * @param n2 The second number.
   * @return A String representation of n1 + n2
   */
  private String addNumericStrings(String n1, String n2) {
    return  n1.length() > n2.length() ? addBigSmallNumber(n1, n2) : addBigSmallNumber(n2, n1);
  }

  /**
   * Method adds the numbers of different lengths
   * @param big the number have with more digits
   * @param small the number in which the digit is less or same.
   * @return sum of the digits as string
   */
  private String addBigSmallNumber (String big, String small) {
    String result = "";
    int operation = 0;
    int delta = big.length() - small.length();
    char smallChar;
    for (int i = big.length()-1; i >= 0; i--){
      // iteration of small numbers, when it ends return '0'
      if (i - delta >= 0) smallChar = small.charAt(i - delta);
      else smallChar = '0';
      // add two digits & remainder from the previous addition
      operation += addDigits(smallChar,big.charAt(i));
      // preparing the result
      if (operation < 10) {
        result = operation + result;
        operation = 0;
      } else {
        result = (operation + "").charAt(1) + result;
        operation = 1;
      }
    }
    // if the number added a digit
    if (operation > 0) result = operation + result;
    return result;
  }

  /**
   * Method converts the letters to the numbers and adds them
   * @param firstDigit a first digit in char
   * @param secondDigit a second digit in char
   * @return sum of the digits as int
   */
  private int addDigits(char firstDigit, char secondDigit) {
    return  firstDigit  + secondDigit - ('0'<<1);
  }

}
