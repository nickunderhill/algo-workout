package com.podopryhora.algoworkout.ds;

/**
 * Queue implemented with a singly linked list using head and tail pointers. Enqueue appends to the
 * tail, and dequeue removes from the head to maintain FIFO order.
 */
public class LinkedListQueue<T> {

  private int size = 0;
  private Node<T> head;
  private Node<T> tail;

  /**
   * Creates an empty queue.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   */
  public LinkedListQueue() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Adds an item to the queue tail.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @param item the item to add
   */
  public void add(T item) {
    // Create a new tail node.
    Node<T> node = new Node<>(item);
    if (isEmpty()) {
      // First element becomes both head and tail.
      head = node;
    } else {
      // Link the new node after the current tail.
      tail.next = node;
    }
    // Advance tail to the new node.
    tail = node;
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
    // Read the current head value.
    T value = head.value;
    // Move head to the next node.
    head = head.next;
    if (head == null) {
      // Queue is now empty; clear tail as well.
      tail = null;
    }
    size--;
    return value;
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
    // Read the current head value.
    return head.value;
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
   * Returns the number of elements in the queue.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return the element count
   */
  public int size() {
    return size;
  }

  private static class Node<T> {
    private final T value;
    private Node<T> next;

    private Node(T x) {
      value = x;
      next = null;
    }
  }
}
