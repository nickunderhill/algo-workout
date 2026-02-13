package com.podopryhora.algoworkout.ds.stack;

import java.util.EmptyStackException;

/**
 * Array-backed stack with dynamic capacity using LIFO order.
 *
 * @param <T> element type stored in the stack
 */
public class DynamicArrayStack<T> implements Stack<T> {

  private Object[] arr;
  private int top;
  private int capacity;

  /**
   * Creates a stack with the given initial capacity. Time complexity: O(n) where n =
   * initialCapacity. Space complexity: O(n).
   *
   * @param initialCapacity initial array capacity
   */
  public DynamicArrayStack(int initialCapacity) {
    // Validate capacity to avoid invalid array creation.
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("Initial capacity must be bigger than 0");
    }
    // Initialize state for an empty stack.
    this.top = -1; // empty stack
    this.capacity = initialCapacity;
    this.arr = new Object[initialCapacity];
  }

  /**
   * Pushes an element onto the top of the stack. Time complexity: O(1) amortized. Space complexity:
   * O(1) amortized.
   *
   * @param x element to push
   */
  @Override
  public void push(T x) {
    // Grow the backing array when full.
    if (size() == capacity) {
      expandArr();
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
  @Override
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
  @Override
  public T peek() {
    // Guard against underflow.
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return elementAt(top);
  }

  /**
   * Checks whether the stack is empty. Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if the stack has no elements
   */
  @Override
  public boolean isEmpty() {
    return top == -1;
  }

  /**
   * Returns the number of elements in the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @return current size
   */
  @Override
  public int size() {
    return top + 1;
  }

  /** Doubles the backing array capacity. Time complexity: O(n). Space complexity: O(n). */
  private void expandArr() {
    // Allocate and copy to the new array.
    int newCapacity = arr.length * 2;
    Object[] newArr = new Object[newCapacity];
    System.arraycopy(arr, 0, newArr, 0, arr.length);
    // Update capacity and reference.
    capacity = newCapacity;
    arr = newArr;
  }

  /**
   * Returns the element stored at the given index. Time complexity: O(1). Space complexity: O(1).
   *
   * @param idx index to read
   * @return element at the given index
   */
  @SuppressWarnings("unchecked")
  private T elementAt(int idx) {
    // Cast is safe because only T instances are stored via push.
    return (T) arr[idx];
  }
}
