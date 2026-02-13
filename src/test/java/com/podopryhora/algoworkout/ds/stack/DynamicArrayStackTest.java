package com.podopryhora.algoworkout.ds.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class DynamicArrayStackTest {

  @Test
  void pushAndPopMaintainLifoOrder() {
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(2);

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
    DynamicArrayStack<String> stack = new DynamicArrayStack<>(2);
    stack.push("a");
    stack.push("b");

    assertEquals("b", stack.peek());
    assertEquals(2, stack.size());
  }

  @Test
  void popThrowsWhenEmpty() {
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(1);

    assertThrows(EmptyStackException.class, stack::pop);
  }

  @Test
  void peekThrowsWhenEmpty() {
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(1);

    assertThrows(EmptyStackException.class, stack::peek);
  }

  @Test
  void sizeTracksElementCount() {
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(2);
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
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(2);
    assertTrue(stack.isEmpty());

    stack.push(1);
    assertFalse(stack.isEmpty());

    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  void nullValuesAreAllowed() {
    DynamicArrayStack<String> stack = new DynamicArrayStack<>(2);
    stack.push(null);

    assertNull(stack.peek());
    assertNull(stack.pop());
    assertTrue(stack.isEmpty());
  }

  @Test
  void constructorRejectsNonPositiveCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new DynamicArrayStack<>(0));
    assertThrows(IllegalArgumentException.class, () -> new DynamicArrayStack<>(-1));
  }

  @Test
  void growsBeyondInitialCapacity() {
    DynamicArrayStack<Integer> stack = new DynamicArrayStack<>(1);

    stack.push(10);
    stack.push(20);
    stack.push(30);

    assertEquals(3, stack.size());
    assertEquals(30, stack.peek());
  }
}
