package com.podopryhora.algoworkout.algorithms.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LinearSearchTest {

    @Test
    void findsExistingPositiveValue() {
        int[] values = {4, 8, 15, 16, 23, 42};
        assertEquals(2, LinearSearch.indexOf(values, 15));
    }

    @Test
    void findsExistingNegativeValue() {
        int[] values = {4, 8, 15, -5, 23, -1, 0};
        assertEquals(3, LinearSearch.indexOf(values, -5));
    }

    @Test
    void returnsMinusOneWhenMissing() {
        int[] values = {4, 8, 15, 16, 23, 42};
        assertEquals(-1, LinearSearch.indexOf(values, 7));
    }

    @Test
    void returnsMinusOneForNullArray() {
        assertEquals(-1, LinearSearch.indexOf(null, 7));
    }
}
