package com.podopryhora.algoworkout.algorithms.search;

/**
 * Linear search scans elements in order until the target is found or the array ends.
 * Time: O(n) average/worst, O(1) best. Space: O(1).
 */
public final class LinearSearch {

    private LinearSearch() {
    }

    /**
     * Searches an array for the first occurrence of a target value.
     *
     * @param values the array of integers to search through. Can be null.
     * @param target the integer value to search for within the array.
     * @return the index of the first occurrence of the target value in the array, or -1 if not found or if the array is null.
     */
    public static int indexOf(int[] values, int target) {
        System.out.printf("Input array: %s, target: %d%n", java.util.Arrays.toString(values), target);
        if (values == null) {
            System.out.println("Error: Null array provided.");
            return -1;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                System.out.println("Target found at index " + i);
                return i;
            }
        }
        System.out.println("Target not found.");
        return -1;
    }
}
