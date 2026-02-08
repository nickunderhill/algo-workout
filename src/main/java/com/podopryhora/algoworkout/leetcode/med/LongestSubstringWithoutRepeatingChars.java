package com.podopryhora.algoworkout.leetcode.med;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * <p>Example:
 *
 * <p>Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3. Note
 * that "bca" and "cab" are also correct answers.
 */
public class LongestSubstringWithoutRepeatingChars {
  /**
   * Returns the length of the longest substring without repeating characters. Time complexity:
   * O(n). Space complexity: O(k), where k is the character set size.
   *
   * @param s input string
   * @return maximum substring length without duplicates
   */
  public static int lengthOfLongestSubstring(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    // Initialize the sliding window and max length.
    int left = 0;
    int maxLength = 1;
    Set<Character> characterSet = new HashSet<>();

    for (int right = 0; right < s.length(); right++) {
      // Shrink the window until the current character is unique.
      while (characterSet.contains(s.charAt(right))) {
        characterSet.remove(s.charAt(left));
        left++;
      }

      // Expand the window and update the max length.
      characterSet.add(s.charAt(right));
      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }
}
