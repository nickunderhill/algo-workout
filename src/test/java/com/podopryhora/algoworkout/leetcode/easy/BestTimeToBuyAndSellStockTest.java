package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BestTimeToBuyAndSellStockTest {

  @Test
  void maxProfitReturnsZeroForNullAndShortInputs() {
    BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

    assertEquals(0, solver.maxProfit(null));
    assertEquals(0, solver.maxProfit(new int[] {}));
    assertEquals(0, solver.maxProfit(new int[] {5}));
  }

  @Test
  void maxProfitReturnsCanonicalLeetCodeExample() {
    BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

    assertEquals(5, solver.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
  }

  @Test
  void maxProfitReturnsZeroWhenPricesAreDecreasing() {
    BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

    assertEquals(0, solver.maxProfit(new int[] {7, 6, 4, 3, 1}));
  }

  @Test
  void maxProfitUsesBestSellAfterLowestBuy() {
    BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

    assertEquals(8, solver.maxProfit(new int[] {5, 3, 6, 1, 9}));
  }
}
