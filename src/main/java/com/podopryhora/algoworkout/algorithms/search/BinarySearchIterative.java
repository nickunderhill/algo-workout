package com.podopryhora.algoworkout.algorithms.search;

/**
 * Binary search on a sorted array by repeatedly halving the search range.
 * Time: O(log n) average/worst, O(1) best. Space: O(1).
 */
public final class BinarySearchIterative {

    private BinarySearchIterative() {
    }

    /**
     * Searches a sorted array for a target value.
     *
     * @param values the sorted array of integers to search through. Can be null.
     * @param target the integer value to search for within the array.
     * @return the index of the target value in the array, or -1 if not found or if the array is null.
     */
    public static int indexOf(int[] values, int target) {
        if (values == null || values.length == 0) {
            return -1;
        }
        int start = 0;
        int end = values.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = values[mid];
            if (midVal == target) {
                return mid;
            }
            if (midVal > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
