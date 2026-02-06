package com.podopryhora.algoworkout.leetcode.easy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FizzBuzzTest {

  private static Stream<Arguments> provideTestData() {
    return Stream.of(
        Arguments.of(3, List.of("1", "2", "Fizz")),
        Arguments.of(5, List.of("1", "2", "Fizz", "4", "Buzz")),
        Arguments.of(
            15,
            List.of(
                "1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz")));
  }

  @ParameterizedTest
  @MethodSource("provideTestData")
  void fizzBuzzTest(int input, List<String> expected) {
    List<String> actual = FizzBuzz.fizzBuzz(input);
    assertEquals(expected, actual);
  }
}
