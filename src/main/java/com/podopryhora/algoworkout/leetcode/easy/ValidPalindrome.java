package com.podopryhora.algoworkout.leetcode.easy;

/**
 * Validates whether a phrase is a palindrome after converting all uppercase letters into lowercase
 * letters and removing all non-alphanumeric characters.
 */
public class ValidPalindrome {

  /**
   * Checks if the cleaned string reads the same forward and backward.
   *
   * <p>Time complexity: O(n). Space complexity: O(n) due to building the normalized string.
   *
   * @param x the input string to validate
   * @return true if the normalized string is a palindrome, false otherwise
   */
  public static boolean isPalindromeWithRegex(String x) {
    if (x == null) {
      return false;
    }

    // Normalize by stripping non-alphanumeric characters and lowercasing.
    x = x.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    int left = 0;
    int right = x.length() - 1;
    // Compare mirrored characters moving inward.
    while (left < right) {
      if (x.charAt(left) != x.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }

  /**
   * Checks if the string is a palindrome using two pointers without extra allocations.
   *
   * <p>Time complexity: O(n). Space complexity: O(1).
   *
   * @param x the input string to validate
   * @return true if the string is a palindrome, false otherwise
   */
  public static boolean isPalindrome(String x) {
    // Treat null as not a palindrome.
    if (x == null) {
      return false;
    }
    int left = 0;
    int right = x.length() - 1;
    // Walk inward while skipping non-alphanumeric characters.
    while (left < right) {
      while (left < right && !Character.isLetterOrDigit(x.charAt(left))) {
        left++;
      }
      while (left < right && !Character.isLetterOrDigit(x.charAt(right))) {
        right--;
      }
      if (left < right) {
        char leftChar = Character.toLowerCase(x.charAt(left));
        char rightChar = Character.toLowerCase(x.charAt(right));
        if (leftChar != rightChar) {
          return false;
        }
        left++;
        right--;
      }
    }
    return true;
  }
}
