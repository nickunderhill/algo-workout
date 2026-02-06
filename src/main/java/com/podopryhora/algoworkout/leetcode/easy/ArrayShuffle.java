package com.podopryhora.algoworkout.leetcode.easy;

/**
 * Interleaves two halves of an array in the form [x1..xn, y1..yn] to [x1, y1, x2, y2, ...].
 *
 * <p>Time complexity: O(n). Space complexity: O(n) for the output array.
 */
public class ArrayShuffle {

  public static int[] shuffle(int[] nums, int n) {
    int length = nums.length;
    int[] answer = new int[length];
    // Interleave values directly from the two halves.
    for (int i = 0; i < n; i++) {
      answer[i * 2] = nums[i];
      answer[i * 2 + 1] = nums[i + n];
    }
    return answer;
  }
}
