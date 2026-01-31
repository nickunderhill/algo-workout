package com.podopryhora.algoworkout.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchIterativeTest {


    @Test
    void returnsIndexWhenTargetPresent() {
        int[] values = {4, 8, 15, 16, 23, 42};
        assertEquals(4, BinarySearchIterative.indexOf(values, 23));
    }

    @Test
    void returnsIndexForNegativeTarget() {
        int[] values = {-20, -14, -5, 0, 8, 15, 16, 23, 42};
        assertEquals(0, BinarySearchIterative.indexOf(values, -20));
    }

    @Test
    void returnsIndexForZeroTarget() {
        int[] values = {-20, -14, -5, 0, 8, 15, 16, 23, 42};
        assertEquals(3, BinarySearchIterative.indexOf(values, 0));
    }

    @Test
    void returnsIndexForSingleElementMatch() {
        int[] values = {5};
        assertEquals(0, BinarySearchIterative.indexOf(values, 5));
    }

    @Test
    void returnsMinusOneForSingleElementMiss() {
        int[] values = {5};
        assertEquals(-1, BinarySearchIterative.indexOf(values, 1));
    }

    @Test
    void returnsMinusOneWhenTargetAboveRange() {
        int[] values = {-20, -14, -5, 0, 8, 15, 16, 23, 42};
        assertEquals(-1, BinarySearchIterative.indexOf(values, 55));
    }

    @Test
    void returnsMinusOneWhenTargetBelowRange() {
        int[] values = {-20, -14, -5, 0, 8, 15, 16, 23, 42};
        assertEquals(-1, BinarySearchIterative.indexOf(values, -55));
    }

    @Test
    void returnsMinusOneForEmptyArray() {
        assertEquals(-1, BinarySearchIterative.indexOf(new int[0], 1));
    }

    @Test
    void returnsMinusOneForNullArray() {
        assertEquals(-1, BinarySearchIterative.indexOf(null, 1));
    }
}
