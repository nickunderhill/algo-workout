package com.podopryhora.algoworkout.ds.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class LinkedListStackTest {

  @Test
  void pushAndPopMaintainLifoOrder() {
    LinkedListStack<Integer> stack = new LinkedListStack<>();

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
    LinkedListStack<String> stack = new LinkedListStack<>();
    stack.push("a");
    stack.push("b");

    assertEquals("b", stack.peek());
    assertEquals(2, stack.size());
  }

  @Test
  void popThrowsWhenEmpty() {
    LinkedListStack<Integer> stack = new LinkedListStack<>();

    assertThrows(EmptyStackException.class, stack::pop);
  }

  @Test
  void peekThrowsWhenEmpty() {
    LinkedListStack<Integer> stack = new LinkedListStack<>();

    assertThrows(EmptyStackException.class, stack::peek);
  }

  @Test
  void sizeTracksElementCount() {
    LinkedListStack<Integer> stack = new LinkedListStack<>();
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
    LinkedListStack<Integer> stack = new LinkedListStack<>();
    assertTrue(stack.isEmpty());

    stack.push(1);
    assertFalse(stack.isEmpty());

    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  void nullValuesAreAllowed() {
    LinkedListStack<String> stack = new LinkedListStack<>();
    stack.push(null);

    assertNull(stack.peek());
    assertNull(stack.pop());
    assertTrue(stack.isEmpty());
  }
}
