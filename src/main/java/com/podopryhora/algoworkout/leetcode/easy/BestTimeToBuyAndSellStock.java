package com.podopryhora.algoworkout.leetcode.easy;

public class BestTimeToBuyAndSellStock {
  /**
   * Returns the maximum profit from one buy and one sell operation.
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

    int maxProfit = 0;
    int buyPrice = Integer.MAX_VALUE;

    for (int i = 0; i < prices.length; i++) {
      if (prices[i] > buyPrice) {
        int currProfit = prices[i] - buyPrice;
        maxProfit = Math.max(currProfit, maxProfit);
      } else {
        buyPrice = prices[i];
      }
    }

    return maxProfit;
  }
}
