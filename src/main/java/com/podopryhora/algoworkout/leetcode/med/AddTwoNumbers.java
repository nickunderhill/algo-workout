package com.podopryhora.algoworkout.leetcode.med;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and
 * return the sum as a linked list. You may assume the two numbers do not contain any leading zero,
 * except the number 0 itself.
 */
public class AddTwoNumbers {

  /**
   * Adds two reversed digit linked lists into a new reversed digit list. Time complexity: O(n).
   * Space complexity: O(n).
   *
   * @param l1 first number as a reversed list
   * @param l2 second number as a reversed list
   * @return sum as a reversed list
   */
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    // Start with no carry; it will be computed after the first sum.
    boolean carryover = false;
    ListNode first = null;
    ListNode current = null;

    while (l1 != null || l2 != null || carryover) {
      // Build the sum of the current digits plus the carry.
      int sum = carryover ? 1 : 0;

      if (l1 != null) {
        // Take the next digit from the first list.
        sum += l1.val;
        l1 = l1.next;
      }

      if (l2 != null) {
        // Take the next digit from the second list.
        sum += l2.val;
        l2 = l2.next;
      }

      // Store the current digit and keep the carry for the next iteration.
      int rem = sum % 10;
      ListNode n = new ListNode(rem, null);

      carryover = sum > 9;

      if (first == null) {
        // Initialize the result list.
        first = n;
        current = n;
      } else {
        // Append to the result list.
        current.next = n;
        current = current.next;
      }
    }

    return first;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder("[");
      ListNode n = this;
      while (n != null) {
        sb.append(n.val);
        if (n.next != null) {
          sb.append(", ");
        } else {
          sb.append("]");
        }
        n = n.next;
      }

      return sb.toString();
    }
  }
}
