package com.podopryhora.algoworkout.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return a string array answer (1-indexed) where: answer[i] == "FizzBuzz" if i
 * is divisible by 3 and 5. answer[i] == "Fizz" if i is divisible by 3. answer[i] == "Buzz" if i is
 * divisible by 5. answer[i] == i (as a string) if none of the above conditions are true.
 */
public class FizzBuzz {

  /**
   * Builds the FizzBuzz output list from 1 to n.
   *
   * <p>Time complexity: O(n). Space complexity: O(n) for the output list.
   *
   * @param n the upper bound (inclusive)
   * @return list of FizzBuzz strings in order
   */
  public static List<String> fizzBuzz(int n) {
    // Prepares the result list with a known size.
    List<String> output = new ArrayList<>(n);

    // Walk through numbers from 1 to n and append the right label.
    for (int i = 1; i <= n; i++) {
      // Compute divisibility once per iteration for readability.
      boolean divisibleBy3 = i % 3 == 0;
      boolean divisibleBy5 = i % 5 == 0;
      if (divisibleBy3 && divisibleBy5) {
        output.add("FizzBuzz");
      } else if (divisibleBy3) {
        output.add("Fizz");
      } else if (divisibleBy5) {
        output.add("Buzz");
      } else {
        output.add(String.valueOf(i));
      }
    }

    // Return the accumulated output.
    return output;
  }
}
