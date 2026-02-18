package com.podopryhora.algoworkout.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStock3Test {

  private static Stream<Arguments> providePricesAndExpectedProfit() {
    return Stream.of(
        Arguments.of(null, 0),
        Arguments.of(new int[] {}, 0),
        Arguments.of(new int[] {5}, 0),
        Arguments.of(new int[] {3, 3, 5, 0, 0, 3, 1, 4}, 6),
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 4),
        Arguments.of(new int[] {7, 6, 4, 3, 1}, 0),
        Arguments.of(new int[] {1, 2, 1, 2, 1, 2}, 2),
        Arguments.of(new int[] {2, 1, 2, 0, 1}, 2),
        Arguments.of(new int[] {1, 4, 2, 7}, 8));
  }

  @ParameterizedTest
  @MethodSource("providePricesAndExpectedProfit")
  void maxProfitSuite(int[] prices, int expectedProfit) {
    assertEquals(expectedProfit, BestTimeToBuyAndSellStock3.maxProfit(prices));
  }
}
