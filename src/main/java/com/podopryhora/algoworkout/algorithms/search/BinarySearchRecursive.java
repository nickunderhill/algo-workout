package com.podopryhora.algoworkout.algorithms.search;

/**
 * Binary search on a sorted array by repeatedly halving the search range.
 * Time: O(log n) average/worst, O(1) best. Space: O(log n) call stack.
 */
public final class BinarySearchRecursive {

    private BinarySearchRecursive() {
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
        return indexOf(values, target, 0, values.length - 1);
    }

    /**
     * Recursively searches sorted array for target via binary search
     */
    private static int indexOf(int[] values, int target, int start, int end) {
        // Terminate when the search window is invalid.
        if (start > end) {
            return -1;
        }
        // Resolve the final single-element window.
        if (start == end) {
            return values[start] == target ? start : -1;
        }

        // Split the window and inspect the midpoint.
        int mid = start + (end - start) / 2;
        // Return immediately on match.
        if (values[mid] == target) {
            return mid;
        // Narrow the search to the left half.
        } else if (values[mid] > target) {
            end = mid - 1;
        // Narrow the search to the right half.
        } else if (values[mid] < target) {
            start = mid + 1;
        }
        // Recurse on the reduced window.
        return indexOf(values, target, start, end);
    }
}
