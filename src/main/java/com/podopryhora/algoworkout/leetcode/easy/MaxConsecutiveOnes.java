package com.podopryhora.algoworkout.leetcode.easy;

/**
 * Finds the length of the longest run of consecutive ones in a binary array.
 *
 * <p>Time complexity: O(n). Space complexity: O(1).
 */
public class MaxConsecutiveOnes {

  /**
   * Returns the maximum number of consecutive ones in the input array.
   *
   * @param nums the binary array to scan
   * @return the length of the longest run of ones
   */
  public static int findMaxConsecutiveOnes(int[] nums) {

    int curCount = 0;
    int maxCount = 0;

    for (int num : nums) {
      if (num == 1) {
        curCount++;
      } else {
        if (curCount > maxCount) {
          maxCount = curCount;
        }
        curCount = 0;
      }
    }
    return Math.max(maxCount, curCount);
  }
}
