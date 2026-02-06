package com.podopryhora.algoworkout.leetcode.med;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestPalindromicSubstringNaiveTest {

  private static Stream<Arguments> provideStringsWithPalindromeSubstrings() {
    return Stream.of(
        Arguments.of("a", "a"),
        Arguments.of("cbbd", "bb"),
        Arguments.of("aabad", "aba"),
        Arguments.of("babad", "bab"),
        Arguments.of("cda1aa", "a1a"),
        Arguments.of("cabaab", "baab"),
        Arguments.of(
            "reifadyqgztixemwswtccodfnchcovrmiooffbbijkecuvlvukecutasfxqcqygltrogrdxlrslbnzktlanycgtniprjlospzhhgdrqcwlukbpsrumxguskubokxcmswjnssbkutdhppsdckuckcbwbxpmcmdicfjxaanoxndlfpqwneytatcbyjmimyawevmgirunvmdvxwdjbiqszwhfhjmrpexfwrbzkipxfowcbqjckaotmmgkrbjvhihgwuszdrdiijkgjoljjdubcbowvxslctleblfmdzmvdkqdxtiylabrwaccikkpnpsgcotxoggdydqnuogmxttcycjorzrtwtcchxrbbknfmxnonbhgbjjypqhbftceduxgrnaswtbytrhuiqnxkivevhprcvhggugrmmxolvfzwadlnzdwbtqbaveoongezoymdrhywxcxvggsewsxckucmncbrljskgsgtehortuvbtrsfisyewchxlmxqccoplhlzwutoqoctgfnrzhqctxaqacmirrqdwsbdpqttmyrmxxawgtjzqjgffqwlxqxwxrkgtzqkgdulbxmfcvxcwoswystiyittdjaqvaijwscqobqlhskhvoktksvmguzfankdigqlegrxxqpoitdtykfltohnzrcgmlnhddcfmawiriiiblwrttveedkxzzagdzpwvriuctvtrvdpqzcdnrkgcnpwjlraaaaskgguxzljktqvzzmruqqslutiipladbcxdwxhmvevsjrdkhdpxcyjkidkoznuagshnvccnkyeflpyjzlcbmhbytxnfzcrnmkyknbmtzwtaceajmnuyjblmdlbjdjxctvqcoqkbaszvrqvjgzdqpvmucerumskjrwhywjkwgligkectzboqbanrsvynxscpxqxtqhthdytfvhzjdcxgckvgfbldsfzxqdozxicrwqyprgnadfxsionkzzegmeynye",
            "uvlvu"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsWithPalindromeSubstrings")
  void longestPalindromeValidInputTest(String input, String expected) {
    String result = LongestPalindromicSubstringNaive.longestPalindrome(input);
    assertEquals(expected, result);
  }

  @Test
  void longestPalindromeNullInputTest() {
    String message =
        assertThrows(
                IllegalArgumentException.class,
                () -> LongestPalindromicSubstringNaive.longestPalindrome(null))
            .getMessage();
    assertEquals("Input must not be null", message);
  }

  @Test
  void longestPalindromeShortInputTest() {
    String input = "";
    String message =
        assertThrows(
                IllegalArgumentException.class,
                () -> LongestPalindromicSubstringNaive.longestPalindrome(input))
            .getMessage();
    assertEquals("Input length must be between 1 and 1000 chars", message);
  }

  @Test
  void longestPalindromeLongInputTest() {
    String input = "a".repeat(1001);
    String message =
        assertThrows(
                IllegalArgumentException.class,
                () -> LongestPalindromicSubstringNaive.longestPalindrome(input))
            .getMessage();
    assertEquals("Input length must be between 1 and 1000 chars", message);
  }
}
