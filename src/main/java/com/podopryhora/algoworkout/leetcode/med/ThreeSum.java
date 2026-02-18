package com.podopryhora.algoworkout.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds all unique triplets whose sum equals zero using sorting and a two-pointer scan.
 *
 * <p>The array is sorted first, then each index is treated as a fixed value while two pointers
 * search the remaining range for complements.
 */
public class ThreeSum {

  /**
   * Returns all unique triplets that sum to zero.
   *
   * <p>Time complexity: O(n^2). Space complexity: O(n) due to the sorted copy (excluding output).
   *
   * @param nums input array of integers
   * @return list of unique triplets that sum to zero
   */
  public static List<List<Integer>> threeSum(int[] nums) {
    // Return result early if an array can't have triplets
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }

    // Use a copy to avoid input array mutation
    int[] sorted = Arrays.copyOf(nums, nums.length);
    List<List<Integer>> results = new ArrayList<>();
    Arrays.sort(sorted);

    for (int i = 0; i < sorted.length - 2; i++) {
      // Skip duplicates
      if (i > 0 && sorted[i] == sorted[i - 1]) {
        continue;
      }
      // Since array is sorted, if the smallest element is positive, no triplets can sum to zero
      if (sorted[i] > 0) {
        break;
      }
      // Break if the smallest possible sum for this i is already positive.
      if (sorted[i] + sorted[i + 1] + sorted[i + 2] > 0) {
        break;
      }
      // Move to the next "i" if the largest possible sum for this "i" is still negative.
      if (sorted[i] + sorted[sorted.length - 2] + sorted[sorted.length - 1] < 0) {
        continue;
      }

      // Scan the range after i with two pointers.
      int left = i + 1;
      int right = sorted.length - 1;
      while (left < right) {
        int sum = sorted[i] + sorted[left] + sorted[right];
        if (sum > 0) {
          right--;
        } else if (sum < 0) {
          left++;
        } else {
          // Store triplet, then skip duplicates on both sides.
          results.add(List.of(sorted[i], sorted[left], sorted[right]));
          left++;
          right--;
          // skip duplicate
          while (left < right && sorted[left] == sorted[left - 1]) {
            left++;
          }
          while (left < right && sorted[right] == sorted[right + 1]) {
            right--;
          }
        }
      }
    }

    return results;
  }
}
