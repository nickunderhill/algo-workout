package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedListQueueTest {

  @Test
  void addAndRemoveMaintainFifoOrder() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();

    queue.add(1);
    queue.add(2);
    queue.add(3);

    assertEquals(1, queue.remove());
    assertEquals(2, queue.remove());
    assertEquals(3, queue.remove());
    assertTrue(queue.isEmpty());
  }

  @Test
  void peekReturnsFrontWithoutRemoving() {
    LinkedListQueue<String> queue = new LinkedListQueue<>();
    queue.add("a");
    queue.add("b");

    assertEquals("a", queue.peek());
    assertEquals(2, queue.size());
  }

  @Test
  void removeThrowsWhenEmpty() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();

    assertThrows(IllegalStateException.class, queue::remove);
  }

  @Test
  void peekThrowsWhenEmpty() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();

    assertThrows(IllegalStateException.class, queue::peek);
  }

  @Test
  void sizeTracksElementCount() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();
    assertEquals(0, queue.size());

    queue.add(5);
    assertEquals(1, queue.size());

    queue.add(6);
    assertEquals(2, queue.size());

    queue.remove();
    assertEquals(1, queue.size());
  }

  @Test
  void isEmptyReflectsState() {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();
    assertTrue(queue.isEmpty());

    queue.add(1);
    assertFalse(queue.isEmpty());

    queue.remove();
    assertTrue(queue.isEmpty());
  }

  @Test
  void nullValuesAreAllowed() {
    LinkedListQueue<String> queue = new LinkedListQueue<>();
    queue.add(null);

    assertNull(queue.peek());
    assertNull(queue.remove());
    assertTrue(queue.isEmpty());
  }
}
