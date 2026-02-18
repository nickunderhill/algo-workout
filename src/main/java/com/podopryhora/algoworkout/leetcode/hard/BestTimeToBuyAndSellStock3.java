package com.podopryhora.algoworkout.leetcode.hard;

/**
 * Solves the "Best Time to Buy and Sell Stock III" problem with at most two transactions.
 *
 * <p>The algorithm uses four DP states: buy1/sell1 for the first transaction and buy2/sell2 for the
 * second transaction. Each price updates these states in order, so each state always represents the
 * best profit achievable up to the current day for that stage.
 */
public class BestTimeToBuyAndSellStock3 {

  /**
   * Returns the maximum profit using at most two non-overlapping buy/sell transactions.
   *
   * <p>Time complexity: O(n). Space complexity: O(1).
   *
   * @param prices stock prices by day
   * @return maximum achievable profit, or 0 if no profitable transaction exists
   */
  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
      return 0;
    }

    // Best balance after the first buy and first sell.
    int buy1 = Integer.MIN_VALUE;
    int sell1 = 0;
    // Best balance after the second buy and second sell.
    int buy2 = Integer.MIN_VALUE;
    int sell2 = 0;

    for (int i = 0; i < prices.length; i++) {
      // Either keep previous state or perform this operation today.
      buy1 = Math.max(buy1, -prices[i]);
      sell1 = Math.max(sell1, prices[i] + buy1);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell2 = Math.max(sell2, prices[i] + buy2);
    }

    return sell2;
  }
}
