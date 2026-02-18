package com.podopryhora.algoworkout.leetcode.easy;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith
 * day.
 *
 * <p>On each day, you may decide to buy and/or sell the stock. You can only hold at most one share
 * of the stock at any time. However, you can sell and buy the stock multiple times on the same day,
 * ensuring you never hold more than one share of the stock.
 *
 * <p>Find and return the maximum profit you can achieve.
 */
public class BestTimeToBuyAndSellStock2 {

  /**
   * Returns the maximum profit with as many non-overlapping buy/sell transactions as needed.
   *
   * <p>Time complexity: O(n). Space complexity: O(1).
   *
   * @param prices stock prices by day
   * @return maximum achievable profit, or 0 if no profitable transaction exists
   */
  public static int maxProfit(int[] prices) {
    // Guard invalid or non-actionable input.
    if (prices == null || prices.length < 2) {
      return 0;
    }

    int totalProfit = 0;

    // Add every upward day-to-day movement as a separate profitable trade.
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i] < prices[i + 1]) {
        int currProfit = prices[i + 1] - prices[i];
        totalProfit += currProfit;
      }
    }

    // Return accumulated profit from all valid ascending segments.
    return totalProfit;
  }
}
