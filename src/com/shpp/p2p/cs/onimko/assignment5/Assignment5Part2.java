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
   *
   * @param n1 The first number.
   * @param n2 The second number.
   * @return A String representation of n1 + n2
   */
  private String addNumericStrings(String n1, String n2) {
    String result = "";
    int operation = 0;
    String big;
    String small;
    if (n1.length() > n2.length()) {
      big = n1;
      small = n2;
    }
    else {
      big = n2;
      small = n1;
    }
    int i = big.length()-1;
    int delta = big.length() - small.length();
    char smallChar;
    while (i >= 0){
      if (i-delta >= 0) smallChar = small.charAt(i-delta);
      else smallChar = '0';
      operation += addDigits(smallChar,big.charAt(i));
      if (operation < 10) {
        result = operation + result;
        operation = 0;
      } else {
        result = (operation+"").charAt(1) +result;
        operation = 1;
      }
      i--;
    }
    if (operation>0) result = operation +result;

    return result;
  }

  private int addDigits(char firstDigit, char secondDigit) {
    return  firstDigit  + secondDigit - ('0'<<1);
  }

}
