package com.podopryhora.algoworkout.ds;

import java.util.Objects;

public class DoublyLinkedList<T> {

  private Node head;
  private Node tail;
  private int size;

  public DoublyLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  // O(1)
  public void addFirst(T value) {
    Node newNode = new Node(value, null, head);
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
    Node newNode = new Node(value, tail, null);
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
  public void insertAfter(Node node, T value) {
    Node newNode = new Node(value, node, node.next);
    if (node.next == null) {
      tail = newNode;
    } else {
      node.next.prev = newNode;
    }
    node.next = newNode;
    size++;
  }

  // O(1)
  public void remove(Node node) {
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
  public Node get(int index) {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException();
    }
    Node node = null;
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
  public Node find(T value) {
    Node currentNode = head;
    while (currentNode != null) {
      if (Objects.equals(value, currentNode.value)) {
        return currentNode;
      }
      currentNode = currentNode.next;
    }
    return null;
  }

  public class Node {

    private final T value;
    private Node prev;
    private Node next;

    private Node(T value, Node prev, Node next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }

    public T getValue() {
      return value;
    }
  }
}
