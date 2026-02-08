package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CircularArrayQueueTest {

  @Test
  void addAndRemoveMaintainFifoOrder() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);

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
    CircularArrayQueue<String> queue = new CircularArrayQueue<>(2);
    queue.add("a");
    queue.add("b");

    assertEquals("a", queue.peek());
    assertFalse(queue.isEmpty());
  }

  @Test
  void removeThrowsWhenEmpty() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(1);

    assertThrows(IllegalStateException.class, queue::remove);
  }

  @Test
  void peekThrowsWhenEmpty() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(1);

    assertThrows(IllegalStateException.class, queue::peek);
  }

  @Test
  void addThrowsWhenFull() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(2);
    queue.add(10);
    queue.add(20);

    assertThrows(IllegalStateException.class, () -> queue.add(30));
  }

  @Test
  void isFullReflectsState() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(2);
    assertFalse(queue.isFull());

    queue.add(1);
    queue.add(2);
    assertTrue(queue.isFull());

    queue.remove();
    assertFalse(queue.isFull());
  }

  @Test
  void wraparoundPreservesOrder() {
    CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
    queue.add(1);
    queue.add(2);
    queue.add(3);

    assertEquals(1, queue.remove());
    queue.add(4);

    assertEquals(2, queue.remove());
    assertEquals(3, queue.remove());
    assertEquals(4, queue.remove());
    assertTrue(queue.isEmpty());
  }

  @Test
  void nullValuesAreAllowed() {
    CircularArrayQueue<String> queue = new CircularArrayQueue<>(2);
    queue.add(null);

    assertNull(queue.peek());
    assertNull(queue.remove());
    assertTrue(queue.isEmpty());
  }

  @Test
  void constructorRejectsNonPositiveCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new CircularArrayQueue<>(0));
    assertThrows(IllegalArgumentException.class, () -> new CircularArrayQueue<>(-1));
  }
}
