package com.podopryhora.algoworkout.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Finds two indices whose values sum to a target using a one-pass hash map.
 *
 * <p>Time complexity: O(n) average, O(n^2) worst case with pathological hashing. Space complexity:
 * O(n) for the map.
 */
public class TwoSum {

  /**
   * Returns indices of two numbers that add up to the target.
   *
   * @param nums the input array of integers
   * @param target the target sum
   * @return a two-element array with the indices, or an empty array if no pair exists
   */
  public static int[] twoSum(int[] nums, int target) {
    // Track seen values to resolve complements in O(1) average time.
    Map<Integer, Integer> numsMap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      // Compute the complement needed to reach the target.
      int complement = target - nums[i];
      // If we have seen the complement, we found the pair.
      if (numsMap.containsKey(complement)) {
        return new int[] {numsMap.get(complement), i};
      }
      // Store current value with its index for future lookups.
      numsMap.put(nums[i], i);
    }
    // No valid pair found.
    return new int[] {};
  }
}
