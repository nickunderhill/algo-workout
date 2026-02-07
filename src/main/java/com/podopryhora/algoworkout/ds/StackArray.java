package com.podopryhora.algoworkout.ds;

import java.util.EmptyStackException;

/**
 * Array-backed stack with fixed capacity using LIFO order.
 *
 * @param <T> element type stored in the stack
 */
public class StackArray<T> {

  private final Object[] arr;
  private int top = -1;

  /**
   * Creates a stack with the given fixed capacity. Time complexity: O(1). Space complexity: O(1).
   *
   * @param capacity maximum number of elements the stack can hold
   */
  public StackArray(int capacity) {
    // Validate capacity to avoid invalid array creation.
    if (capacity < 1) {
      throw new IllegalArgumentException("StackArray minimum capacity is 1");
    }
    arr = new Object[capacity];
  }

  /**
   * Pushes an element onto the top of the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @param x element to push
   */
  public void push(T x) {
    // Guard against overflow in a fixed-capacity stack.
    if (top >= arr.length - 1) {
      throw new IllegalStateException("Maximum StackArray capacity is reached: " + arr.length);
    }

    // Advance top and store the new element.
    top++;
    arr[top] = x;
  }

  /**
   * Pops the top element from the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @return element at the top
   */
  public T pop() {
    // Guard against underflow.
    if (isEmpty()) {
      throw new EmptyStackException();
    }

    // Read, clear, and move the top pointer down.
    T x = elementAt(top);
    arr[top] = null;
    top--;
    return x;
  }

  /**
   * Returns the top element without removing it. Time complexity: O(1). Space complexity: O(1).
   *
   * @return element at the top
   */
  public T peek() {
    // Guard against underflow.
    if (top == -1) {
      throw new EmptyStackException();
    }

    return elementAt(top);
  }

  /**
   * Checks whether the stack is empty. Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if the stack has no elements
   */
  public boolean isEmpty() {
    return top == -1;
  }

  /**
   * Returns the number of elements in the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @return current size
   */
  public int size() {
    return top + 1;
  }

  /**
   * Returns the element stored at the given index. Time complexity: O(1). Space complexity: O(1).
   *
   * @param index index to read
   * @return element at the given index
   */
  @SuppressWarnings("unchecked")
  private T elementAt(int index) {
    // Cast is safe because only T instances are stored via push.
    return (T) arr[index];
  }
}
