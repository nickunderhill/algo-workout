package com.podopryhora.algoworkout.leetcode.easy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PalindromeNumberTest {

  private static Stream<Arguments> provideExamples() {
    return Stream.of(
        Arguments.of(121, true),
        Arguments.of(1221, true),
        Arguments.of(-121, false),
        Arguments.of(10, false),
        Arguments.of(0, true));
  }

  @ParameterizedTest
  @MethodSource("provideExamples")
  void palindromeNumberTest(int input, boolean expected) {
    Assertions.assertEquals(expected, PalindromeNumber.isPalindrome(input));
  }

  @ParameterizedTest
  @MethodSource("provideExamples")
  void palindromeNumberStringApproachTest(int input, boolean expected) {
    Assertions.assertEquals(expected, PalindromeNumber.isPalindromeStringApproach(input));
  }
}
