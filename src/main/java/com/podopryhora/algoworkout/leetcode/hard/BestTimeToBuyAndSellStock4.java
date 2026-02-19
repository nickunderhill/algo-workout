package com.podopryhora.algoworkout.leetcode.hard;

import java.util.Arrays;

/**
 * Solves the "Best Time to Buy and Sell Stock IV" problem with at most {@code k} transactions.
 *
 * <p>The algorithm keeps two DP arrays per transaction index:
 * {@code buy[j]} is the best balance after buying the {@code (j + 1)}-th stock and
 * {@code sell[j]} is the best balance after selling the {@code (j + 1)}-th stock.
 * For large {@code k}, it switches to the linear greedy solution equivalent to unlimited
 * transactions.
 */
public class BestTimeToBuyAndSellStock4 {

  /**
   * Returns the maximum profit using at most {@code k} non-overlapping buy/sell transactions.
   *
   * <p>Time complexity: O(n * k), or O(n) when {@code k >= n / 2}. Space complexity: O(k).
   *
   * @param k maximum number of allowed transactions
   * @param prices stock prices by day
   * @return maximum achievable profit, or 0 if no profitable transaction exists
   */
  public static int maxProfit(int k, int[] prices) {
    if (k <= 0 || prices == null || prices.length < 2) {
      return 0;
    }

    int n = prices.length;

    if (k >= n / 2) {
      int totalProfit = 0;
      for (int i = 0; i < n - 1; i++) {
        totalProfit += Math.max(0, prices[i + 1] - prices[i]);
      }
      return totalProfit;
    }
    // DP states for each transaction stage.
    int[] buy = new int[k];
    int[] sell = new int[k];

    // Initialize "not bought yet" states to negative infinity.
    Arrays.fill(buy, Integer.MIN_VALUE);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < k; j++) {
        // Either keep the previous state or execute the operation today.
        buy[j] = Math.max(buy[j], (j == 0 ? 0 : sell[j - 1]) - prices[i]);
        sell[j] = Math.max(sell[j], buy[j] + prices[i]);
      }
    }

    return sell[k - 1];
  }
}
