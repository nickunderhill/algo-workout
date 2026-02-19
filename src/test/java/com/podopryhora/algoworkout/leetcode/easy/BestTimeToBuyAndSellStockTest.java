package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BestTimeToBuyAndSellStockTest {

  @Test
  void maxProfitReturnsZeroForNullAndShortInputs() {
    assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(null));
    assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {}));
    assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {5}));
  }

  @Test
  void maxProfitReturnsCanonicalLeetCodeExample() {

    assertEquals(5, BestTimeToBuyAndSellStock.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
  }

  @Test
  void maxProfitReturnsZeroWhenPricesAreDecreasing() {
    assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {7, 6, 4, 3, 1}));
  }

  @Test
  void maxProfitUsesBestSellAfterLowestBuy() {
    assertEquals(8, BestTimeToBuyAndSellStock.maxProfit(new int[] {5, 3, 6, 1, 9}));
  }
}
