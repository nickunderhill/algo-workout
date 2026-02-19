package com.podopryhora.algoworkout.leetcode.hard;

/**
 * Solves the "Best Time to Buy and Sell Stock with Cooldown" problem.
 *
 * <p>The algorithm tracks three DP states per day:
 * {@code hold} (currently holding stock), {@code sold} (sold today), and
 * {@code rest} (not holding and not selling today). Cooldown is enforced by allowing new buys only
 * from the previous {@code rest} state.
 */
public class BestTimeToBuyAndSellStockWithCooldown {

  /**
   * Returns the maximum profit with unlimited transactions and a one-day cooldown after each sell.
   *
   * <p>Time complexity: O(n). Space complexity: O(1).
   *
   * @param prices stock prices by day
   * @return maximum achievable profit, or 0 if no profitable transaction exists
   */
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
      return 0;
    }

    // DP states for the current day.
    int hold = Integer.MIN_VALUE;
    int sold = 0;
    int rest = 0;

    for (int i = 0; i < prices.length; i++) {
      // Snapshot previous rest before it is updated.
      int prevRest = rest;
      // Either keep resting or transition from a sell to cooldown/rest.
      rest = Math.max(sold, rest);
      // Sell stock today if we were holding.
      sold = hold + prices[i];
      // Buy today only from previous rest to respect cooldown.
      hold = Math.max(hold, prevRest - prices[i]);
    }

    return Math.max(sold, rest);
  }
}
