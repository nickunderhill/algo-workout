package com.podopryhora.algoworkout.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStock4Test {

  private static Stream<Arguments> provideInputsAndExpectedProfit() {
    return Stream.of(
        Arguments.of(2, null, 0),
        Arguments.of(2, new int[] {}, 0),
        Arguments.of(2, new int[] {5}, 0),
        Arguments.of(0, new int[] {2, 4, 1}, 0),
        Arguments.of(-1, new int[] {2, 4, 1}, 0),
        Arguments.of(2, new int[] {2, 4, 1}, 2),
        Arguments.of(2, new int[] {3, 2, 6, 5, 0, 3}, 7),
        Arguments.of(1, new int[] {3, 2, 6, 5, 0, 3}, 4),
        Arguments.of(100, new int[] {1, 2, 1, 2, 1, 2}, 3),
        Arguments.of(2, new int[] {5, 4, 3, 2, 1}, 0));
  }

  @ParameterizedTest
  @MethodSource("provideInputsAndExpectedProfit")
  void maxProfitSuite(int k, int[] prices, int expectedProfit) {
    BestTimeToBuyAndSellStock4 solver = new BestTimeToBuyAndSellStock4();
    assertEquals(expectedProfit, solver.maxProfit(k, prices));
  }
}
