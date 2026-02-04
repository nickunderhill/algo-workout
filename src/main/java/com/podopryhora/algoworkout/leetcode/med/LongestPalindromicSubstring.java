package com.podopryhora.algoworkout.leetcode.med;

/**
 * Finds the longest palindromic substring using an expand-around-center strategy. Time complexity:
 * O(n^2). Space complexity: O(1).
 */
public class LongestPalindromicSubstring {

  // O(n^2) time from checking each center; O(1) extra space.
  public static String longestPalindrome(String s) {

    if (s == null) {
      throw new IllegalArgumentException("Input must not be null");
    }
    int inputLength = s.length();
    if (inputLength < 1 || inputLength > 1000) {
      throw new IllegalArgumentException("Input length must be between 1 and 1000 chars");
    }
    if (inputLength == 1) {
      return s;
    }
    int bestStart = 0;
    int bestEnd = 0;
    // Iterate over each index as a potential palindrome center (O(n) centers).
    for (int center = 0; center < inputLength; center++) {
      // Expand around a single-character center (odd length).
      int oddLength = expandAroundCenter(s, center, center);
      if (oddLength > bestEnd - bestStart + 1) {
        // Update the best window boundaries from this odd-length center.
        bestStart = center - (oddLength - 1) / 2;
        bestEnd = center + (oddLength - 1) / 2;
      }
      // Expand around a two-character center (even length).
      int evenLength = expandAroundCenter(s, center, center + 1);
      if (evenLength > bestEnd - bestStart + 1) {
        // Update the best window boundaries from this even-length center.
        bestStart = center - (evenLength - 1) / 2;
        bestEnd = center + evenLength / 2;
      }
    }
    return s.substring(bestStart, bestEnd + 1);
  }

  private static int expandAroundCenter(String s, int left, int right) {
    // Expand from a center while the window remains a palindrome (O(n) per center).
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    // Return the length of the last valid palindrome window (right - 1) - (left + 1) + 1
    return right - left - 1;
  }
}
