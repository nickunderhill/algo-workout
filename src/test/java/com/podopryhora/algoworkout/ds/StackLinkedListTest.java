package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class StackLinkedListTest {

  @Test
  void pushAndPopMaintainLifoOrder() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();

    stack.push(1);
    stack.push(2);
    stack.push(3);

    assertEquals(3, stack.pop());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
    assertTrue(stack.isEmpty());
  }

  @Test
  void peekReturnsTopWithoutRemoving() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push("a");
    stack.push("b");

    assertEquals("b", stack.peek());
    assertEquals(2, stack.size());
  }

  @Test
  void popThrowsWhenEmpty() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();

    assertThrows(EmptyStackException.class, stack::pop);
  }

  @Test
  void peekThrowsWhenEmpty() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();

    assertThrows(EmptyStackException.class, stack::peek);
  }

  @Test
  void sizeTracksElementCount() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    assertEquals(0, stack.size());

    stack.push(5);
    assertEquals(1, stack.size());

    stack.push(6);
    assertEquals(2, stack.size());

    stack.pop();
    assertEquals(1, stack.size());
  }

  @Test
  void isEmptyReflectsState() {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    assertTrue(stack.isEmpty());

    stack.push(1);
    assertFalse(stack.isEmpty());

    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  void nullValuesAreAllowed() {
    StackLinkedList<String> stack = new StackLinkedList<>();
    stack.push(null);

    assertNull(stack.peek());
    assertNull(stack.pop());
    assertTrue(stack.isEmpty());
  }
}
