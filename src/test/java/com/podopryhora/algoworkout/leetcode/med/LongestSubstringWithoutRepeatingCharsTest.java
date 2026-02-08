package com.podopryhora.algoworkout.leetcode.med;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestSubstringWithoutRepeatingCharsTest {

  private static Stream<Arguments> provideStrings() {
    return Stream.of(
        Arguments.of("abcabcbb", 3),
        Arguments.of("bbbbb", 1),
        Arguments.of("pwwkew", 3),
        Arguments.of("dvdf", 3),
        Arguments.of("abba", 2),
        Arguments.of("tmmzuxt", 5),
        Arguments.of("aab", 2),
        Arguments.of("abcde", 5),
        Arguments.of("a", 1),
        Arguments.of(" ", 1),
        Arguments.of("", 0),
        Arguments.of(null, 0),
        Arguments.of("au", 2));
  }

  @ParameterizedTest
  @MethodSource("provideStrings")
  void lengthOfLongestSubstring(String input, int expected) {
    int actual = LongestSubstringWithoutRepeatingChars.lengthOfLongestSubstring(input);
    assertEquals(expected, actual);
  }
}
