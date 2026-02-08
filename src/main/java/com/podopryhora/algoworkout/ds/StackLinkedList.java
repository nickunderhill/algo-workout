package com.podopryhora.algoworkout.ds;

import java.util.EmptyStackException;

/**
 * Singly linked-list stack with LIFO order and constant-time operations.
 *
 * @param <T> element type stored in the stack
 */
public class StackLinkedList<T> {

  private int size = 0;
  private Node<T> head;

  /** Creates an empty stack. Time complexity: O(1). Space complexity: O(1). */
  public StackLinkedList() {
    this.head = null;
  }

  /**
   * Pushes an element onto the top of the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @param x element to push
   */
  public void push(T x) {
    // Create a new node and link it ahead of the current head.
    Node<T> n = new Node<>(x);
    n.next = head;
    // Update head to the new node.
    head = n;
    // Increment size after a successful push.
    size++;
  }

  /**
   * Pops the top element from the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @return element at the top
   */
  public T pop() {
    // Guard against underflow.
    if (head == null) {
      throw new EmptyStackException();
    }
    // Read and unlink the top node.
    T value = head.value;
    head = head.next;
    // Decrement size after removal.
    size--;
    return value;
  }

  /**
   * Returns the top element without removing it. Time complexity: O(1). Space complexity: O(1).
   *
   * @return element at the top
   */
  public T peek() {
    // Guard against underflow.
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return head.value;
  }

  /**
   * Checks whether the stack is empty. Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if the stack has no elements
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns the number of elements in the stack. Time complexity: O(1). Space complexity: O(1).
   *
   * @return current size
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
