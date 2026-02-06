package com.podopryhora.algoworkout.leetcode.med;

/**
 * Finds the longest palindromic substring by checking every substring. Time complexity: O(n^3).
 * Space complexity: O(1).
 */
public class LongestPalindromicSubstringNaive {

  public static String longestPalindrome(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Input must not be null");
    }

    int inputLength = s.length();
    if (inputLength < 1 || inputLength > 1000) {
      throw new IllegalArgumentException("Input length must be between 1 and 1000 chars");
    }

    int bestStart = 0;
    int maxLength = 1;
    // Enumerate every substring start.
    for (int left = 0; left < inputLength; left++) {
      // Enumerate every substring end for this start (O(n^2) total pairs).
      for (int right = left; right < inputLength; right++) {
        // Each palindrome check costs O(n) in the worst case.
        if (isPalindrome(s, left, right) && right - left + 1 > maxLength) {
          bestStart = left;
          maxLength = right - bestStart + 1;
        }
      }
    }
    return s.substring(bestStart, bestStart + maxLength);
  }

  public static boolean isPalindrome(String input, int left, int right) {
    // Two-pointer scan over the substring (O(n) time).
    while (left < right) {
      if (input.charAt(left) != input.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
}
