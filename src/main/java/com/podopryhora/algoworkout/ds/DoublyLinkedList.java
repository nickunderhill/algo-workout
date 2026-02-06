package com.podopryhora.algoworkout.ds;

import java.util.Objects;

/**
 * Doubly linked list is a data structure where each node has references to both previous and next
 * nodes. It supports bidirectional traversal and O(1) updates given a node reference.
 *
 * <p>Time complexity: add/remove at head or tail O(1), insert after a known node O(1), remove a
 * known node O(1), find by value or index O(n).
 *
 * <p>Space complexity: O(n) for n nodes, plus O(1) auxiliary space per operation.
 *
 * <p>Useful when frequent insertions/removals near the ends or around known nodes are needed and
 * bidirectional traversal is required.
 */
public class DoublyLinkedList<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public DoublyLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  // O(1)
  public void addFirst(T value) {
    Node<T> newNode = new Node<>(value, null, head);
    if (head == null) {
      tail = newNode;
    } else {
      head.prev = newNode;
    }
    head = newNode;
    size++;
  }

  // O(1)
  public void addLast(T value) {
    Node<T> newNode = new Node<>(value, tail, null);
    if (tail == null) {
      head = newNode;
    } else {
      tail.next = newNode;
    }
    tail = newNode;
    size++;
  }

  // O(1)
  public T removeFirst() {
    if (head == null) {
      return null;
    }
    T value = head.value;
    head = head.next;
    if (head == null) {
      tail = null;
    } else {
      head.prev = null;
    }
    size--;
    return value;
  }

  // O(1)
  public T removeLast() {
    if (tail == null) {
      return null;
    }
    T value = tail.value;
    tail = tail.prev;
    if (tail == null) {
      head = null;
    } else {
      tail.next = null;
    }
    size--;
    return value;
  }

  // O(1)
  public void insertAfter(Node<T> node, T value) {
    Node<T> newNode = new Node<>(value, node, node.next);
    if (node.next == null) {
      tail = newNode;
    } else {
      node.next.prev = newNode;
    }
    node.next = newNode;
    size++;
  }

  // O(1)
  public void remove(Node<T> node) {
    if (node.prev == null) {
      head = node.next;
    } else {
      node.prev.next = node.next;
    }
    if (node.next == null) {
      tail = node.prev;
    } else {
      node.next.prev = node.prev;
    }
    node.next = null;
    node.prev = null;
    size--;
  }

  // O(n)
  public Node<T> get(int index) {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException();
    }
    Node<T> node;
    if (index < size / 2) {
      node = head;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
    } else {
      node = tail;
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
    }
    return node;
  }

  // O(n)
  public Node<T> find(T value) {
    Node<T> currentNode = head;
    while (currentNode != null) {
      if (Objects.equals(value, currentNode.value)) {
        return currentNode;
      }
      currentNode = currentNode.next;
    }
    return null;
  }

  public static class Node<T> {

    private final T value;
    private Node<T> prev;
    private Node<T> next;

    private Node(T value, Node<T> prev, Node<T> next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }

    public T getValue() {
      return value;
    }
  }
}
