package com.podopryhora.algoworkout.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStockWithCooldownTest {

  private static Stream<Arguments> providePricesAndExpectedProfit() {
    return Stream.of(
        Arguments.of(null, 0),
        Arguments.of(new int[] {}, 0),
        Arguments.of(new int[] {1}, 0),
        Arguments.of(new int[] {1, 2, 3, 0, 2}, 3),
        Arguments.of(new int[] {2, 1, 4}, 3),
        Arguments.of(new int[] {1, 2, 4}, 3),
        Arguments.of(new int[] {6, 1, 6, 4, 3, 0, 2}, 7),
        Arguments.of(new int[] {5, 4, 3, 2, 1}, 0));
  }

  @ParameterizedTest
  @MethodSource("providePricesAndExpectedProfit")
  void maxProfitSuite(int[] prices, int expectedProfit) {
    BestTimeToBuyAndSellStockWithCooldown solver = new BestTimeToBuyAndSellStockWithCooldown();
    assertEquals(expectedProfit, solver.maxProfit(prices));
  }
}
