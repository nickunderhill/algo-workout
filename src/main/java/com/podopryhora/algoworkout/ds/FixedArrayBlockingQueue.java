package com.podopryhora.algoworkout.ds;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fixed-capacity blocking queue backed by a circular array and guarded by a single lock.
 *
 * <p>Producers wait when the buffer is full, and consumers wait when it is empty. The queue keeps
 * FIFO order while providing bounded memory usage for producer-consumer workloads.
 */
public class FixedArrayBlockingQueue<T> {

  private final ReentrantLock lock;
  private final Condition notFull;
  private final Condition notEmpty;
  private final Object[] arr;
  private int size = 0;
  private int head = 0;
  private int tail = 0;

  public FixedArrayBlockingQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be > 0");
    }
    this.lock = new ReentrantLock();
    this.notFull = lock.newCondition();
    this.notEmpty = lock.newCondition();
    this.arr = new Object[capacity];
  }

  /**
   * Adds an item to the queue tail, waiting until capacity becomes available.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @param item the item to add
   * @throws InterruptedException if the thread is interrupted while waiting for the lock or space
   */
  public void put(T item) throws InterruptedException {
    lock.lockInterruptibly();
    try {
      while (isFull()) {
        notFull.await();
      }
      // Store the item at the current tail position.
      arr[tail] = item;
      // Advance the tail index with wraparound.
      tail = (tail + 1) % arr.length;
      size++;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Removes and returns the item at the queue head, waiting until an element is available.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return the removed item
   * @throws InterruptedException if the thread is interrupted while waiting for the lock or data
   */
  @SuppressWarnings("unchecked")
  public T take() throws InterruptedException {
    lock.lockInterruptibly();
    try {
      while (isEmpty()) {
        notEmpty.await();
      }
      // Read and clear the current head slot to avoid retaining references.
      T item = (T) arr[head];
      arr[head] = null;
      // Advance the head index with wraparound.
      head = (head + 1) % arr.length;
      size--;
      notFull.signal();
      return item;
    } finally {
      lock.unlock();
    }
  }

  private boolean isFull() {
    return size == arr.length;
  }

  private boolean isEmpty() {
    return size == 0;
  }
}
