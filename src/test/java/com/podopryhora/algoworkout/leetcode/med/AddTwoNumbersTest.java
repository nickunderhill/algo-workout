package com.podopryhora.algoworkout.leetcode.med;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.podopryhora.algoworkout.leetcode.med.AddTwoNumbers.ListNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AddTwoNumbersTest {

  private static Stream<Arguments> provideLists() {
    return Stream.of(
        Arguments.of(new int[] {2, 4, 3}, new int[] {5, 6, 4}, new int[] {7, 0, 8}),
        Arguments.of(new int[] {0}, new int[] {0}, new int[] {0}),
        Arguments.of(new int[] {9, 9, 9}, new int[] {1}, new int[] {0, 0, 0, 1}),
        Arguments.of(new int[] {1, 8}, new int[] {0}, new int[] {1, 8}),
        Arguments.of(new int[] {9, 9}, new int[] {9, 9, 9}, new int[] {8, 9, 0, 1}));
  }

  @ParameterizedTest
  @MethodSource("provideLists")
  void addTwoNumbersValidInputSuite(int[] l1Values, int[] l2Values, int[] expectedValues) {
    ListNode l1 = toList(l1Values);
    ListNode l2 = toList(l2Values);

    ListNode actual = AddTwoNumbers.addTwoNumbers(l1, l2);

    assertArrayEquals(expectedValues, toArray(actual));
  }

  private static ListNode toList(int[] values) {
    ListNode head = null;
    ListNode current = null;

    for (int value : values) {
      ListNode node = new ListNode(value, null);
      if (head == null) {
        head = node;
        current = node;
      } else {
        current.next = node;
        current = current.next;
      }
    }

    return head;
  }

  private static int[] toArray(ListNode node) {
    List<Integer> values = new ArrayList<>();
    ListNode current = node;

    while (current != null) {
      values.add(current.val);
      current = current.next;
    }

    int[] result = new int[values.size()];
    for (int i = 0; i < values.size(); i++) {
      result[i] = values.get(i);
    }

    return result;
  }
}
