package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStock2Test {

  private static Stream<Arguments> providePricesAndExpectedProfit() {
    return Stream.of(
        Arguments.of(null, 0),
        Arguments.of(new int[] {}, 0),
        Arguments.of(new int[] {5}, 0),
        Arguments.of(new int[] {7, 1, 5, 3, 6, 4}, 7),
        Arguments.of(new int[] {7, 6, 4, 3, 1}, 0),
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 4),
        Arguments.of(new int[] {1, 1, 2, 2, 3}, 2),
        Arguments.of(new int[] {1, 2, 1, 3, 9}, 9),
        Arguments.of(new int[] {8, 1, 1, 7, 2, 1, 9, 3, 1, 4, 8}, 21));
  }

  @ParameterizedTest
  @MethodSource("providePricesAndExpectedProfit")
  void maxProfitSuite(int[] prices, int expectedProfit) {
    assertEquals(expectedProfit, BestTimeToBuyAndSellStock2.maxProfit(prices));
  }
}
