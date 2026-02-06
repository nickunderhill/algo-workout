package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ValidPalindromeTest {

  private static Stream<Arguments> provideExamples() {
    return Stream.of(
        Arguments.of("A man, a plan, a canal: Panama", true),
        Arguments.of("race a car", false),
        Arguments.of("", true),
        Arguments.of(" ", true),
        Arguments.of("0P", false),
        Arguments.of("No 'x' in Nixon", true),
        Arguments.of(".,", true),
        Arguments.of("Able was I, ere I saw Elba!", true),
        Arguments.of(null, false));
  }

  @ParameterizedTest
  @MethodSource("provideExamples")
  void validPalindromeNormalizedApproach(String input, boolean expected) {
    assertEquals(expected, ValidPalindrome.isPalindrome(input));
  }

  @ParameterizedTest
  @MethodSource("provideExamples")
  void validPalindromeInPlaceApproach(String input, boolean expected) {
    assertEquals(expected, ValidPalindrome.isPalindromeWithRegex(input));
  }
}
