package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TwoSumTest {

  private static Stream<Arguments> provideIntArraysAndTargetSum() {
    return Stream.of(
        Arguments.of(new int[] {2, 7, 11, 15}, 9, new int[] {0, 1}),
        Arguments.of(new int[] {3, 2, 4}, 6, new int[] {1, 2}),
        Arguments.of(new int[] {3, 3}, 6, new int[] {0, 1}),
        Arguments.of(new int[] {-1, -2, -3, -4, -5}, -8, new int[] {2, 4}),
        Arguments.of(new int[] {0, 4, 3, 0}, 0, new int[] {0, 3}),
        Arguments.of(new int[] {1, 5, 1, 5}, 6, new int[] {0, 1}),
        Arguments.of(new int[] {1, 2, 3}, 7, new int[] {}));
  }

  @ParameterizedTest
  @MethodSource("provideIntArraysAndTargetSum")
  void twoSumValidInputSuite(int[] inputArr, int inputTarget, int[] expected) {
    int[] actual = TwoSum.twoSum(inputArr, inputTarget);
    assertArrayEquals(expected, actual);
  }
}
