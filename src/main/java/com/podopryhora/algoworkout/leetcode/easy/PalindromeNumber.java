package com.podopryhora.algoworkout.leetcode.easy;

/**
 * Checks whether an integer reads the same forward and backward.
 *
 * <p>Time complexity: O(n) where n is the number of digits. Space complexity: O(1) for the
 * arithmetic approach, O(n) for the string approach.
 */
public class PalindromeNumber {

  /**
   * Determines whether the given integer is a palindrome using digit reversal.
   *
   * @param x the integer to check
   * @return true if the integer is a palindrome, false otherwise
   */
  public static boolean isPalindrome(int x) {
    // Negative numbers are not palindromes.
    if (x < 0) {
      return false;
    }

    // Zero is a palindrome.
    if (x == 0) {
      return true;
    }

    int reversed = 0;
    int remainder = x;
    // Reverse digits to compare with the original value.
    while (remainder > 0) {
      reversed = reversed * 10 + remainder % 10;
      remainder = remainder / 10;
    }

    return reversed == x;
  }

  /**
   * Determines whether the given integer is a palindrome using string conversion.
   *
   * @param x the integer to check
   * @return true if the integer is a palindrome, false otherwise
   */
  public static boolean isPalindromeStringApproach(int x) {
    // Negative numbers are not palindromes.
    if (x < 0) {
      return false;
    }

    // Zero is a palindrome.
    if (x == 0) {
      return true;
    }

    String str = String.valueOf(x);
    int left = 0;
    int right = str.length() - 1;
    // Move inwards from the leftmost and the rightmost chars of the string until left and right are
    // equal
    while (left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }
}
