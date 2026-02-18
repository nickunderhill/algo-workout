package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.podopryhora.algoworkout.leetcode.med.ThreeSum;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class ThreeSumTest {

  @Test
  void threeSumCanonicalInputReturnsExpectedTriplets() {
    List<List<Integer>> actual = ThreeSum.threeSum(new int[] {-1, 0, 1, 2, -1, -4});

    List<List<Integer>> expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
    assertEquals(normalize(expected), normalize(actual));
  }

  @Test
  void threeSumAllZerosReturnsSingleTriplet() {
    List<List<Integer>> actual = ThreeSum.threeSum(new int[] {0, 0, 0, 0});

    List<List<Integer>> expected = List.of(List.of(0, 0, 0));
    assertEquals(normalize(expected), normalize(actual));
  }

  @Test
  void threeSumNullAndShortInputsReturnEmpty() {
    assertEquals(List.of(), ThreeSum.threeSum(null));
    assertEquals(List.of(), ThreeSum.threeSum(new int[] {}));
    assertEquals(List.of(), ThreeSum.threeSum(new int[] {1, -1}));
  }

  @Test
  void threeSumDoesNotMutateInputArray() {
    int[] input = new int[] {3, -2, -1, 0, 1, 2};
    int[] snapshot = input.clone();

    ThreeSum.threeSum(input);

    assertArrayEquals(snapshot, input);
  }

  private static List<List<Integer>> normalize(List<List<Integer>> triplets) {
    List<List<Integer>> normalized = new ArrayList<>();
    for (List<Integer> triplet : triplets) {
      List<Integer> sortedTriplet = new ArrayList<>(triplet);
      sortedTriplet.sort(Integer::compareTo);
      normalized.add(sortedTriplet);
    }
    normalized.sort(
        Comparator.comparing((List<Integer> t) -> t.get(0))
            .thenComparing(t -> t.get(1))
            .thenComparing(t -> t.get(2)));
    return normalized;
  }
}
