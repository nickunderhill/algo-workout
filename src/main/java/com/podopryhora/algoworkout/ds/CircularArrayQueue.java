package com.podopryhora.algoworkout.ds;

/**
 * Fixed-capacity circular queue backed by an array with head and tail indices. Elements are
 * enqueued at the tail and dequeued from the head, wrapping around the array when reaching the end.
 *
 * <p>Useful for bounded buffering, producer-consumer pipelines, and rate-limited processing where
 * FIFO order and predictable memory use matter.
 *
 * <p>Limitations: fixed capacity requires handling overflow, no random access, and the array must
 * be preallocated to the maximum size.
 */
public class CircularArrayQueue<T> {

  private final int capacity;
  private final T[] arr;
  private int head = 0;
  private int tail = 0;
  private int size = 0;

  /**
   * Creates a fixed-capacity queue.
   *
   * <p>Time complexity: O(n) where n = capacity. Space complexity: O(n).
   *
   * @param capacity the maximum number of elements allowed
   */
  @SuppressWarnings("unchecked")
  public CircularArrayQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive: " + capacity);
    }
    this.capacity = capacity;
    arr = (T[]) new Object[capacity];
  }

  /**
   * Checks whether the queue is empty.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if the queue has no elements
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks whether the queue is full.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if the queue is at its fixed capacity
   */
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Adds an item to the queue tail.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @param item the item to add
   */
  public void add(T item) {
    if (isFull()) {
      throw new IllegalStateException(
          "CircularArrayQueue overflow: fixed capacity " + capacity + " reached");
    }
    // Store item at the current tail position.
    arr[tail] = item;
    // Advance tail index with wraparound.
    tail = (tail + 1) % capacity;
    size++;
  }

  /**
   * Removes and returns the item at the queue head.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return the removed item
   */
  public T remove() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    // Read the item at the head position.
    T item = arr[head];
    // Clear the slot to avoid retaining references.
    arr[head] = null;
    // Advance head index with wraparound.
    head = (head + 1) % capacity;
    size--;
    return item;
  }

  /**
   * Returns the item at the queue head without removing it.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return the item at the head
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    // Read the item at the head position.
    return arr[head];
  }
}
