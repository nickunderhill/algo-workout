package com.podopryhora.algoworkout.ds.stack;

/**
 * A last-in-first-out (LIFO) collection of elements.
 * <p>
 * This interface intentionally does not prescribe a single concrete behavior
 * for operations on an empty stack or for {@code null} elements; instead,
 * implementations must clearly document their specific behavior, including:
 * </p>
 * <ul>
 *   <li>whether {@code null} values are permitted as elements,</li>
 *   <li>what happens when {@link #pop()} or {@link #peek()} are invoked on
 *       an empty stack (for example, throwing a runtime exception), and</li>
 *   <li>any runtime exceptions that may be thrown by the methods in this
 *       interface.</li>
 * </ul>
 *
 * @param <T> the type of elements held in this stack
 */
public interface Stack<T> {

  /**
   * Pushes an element onto the top of the stack.
   *
   * @param x the element to be pushed onto this stack
   * @throws RuntimeException if the element cannot be added for any
   *                          implementation-specific reason (for example,
   *                          capacity limits)
   * @implSpec Implementations should document whether {@code null} values are
   *           permitted and what happens if a disallowed value is provided.
   */
  void push(T x);

  /**
   * Removes and returns the element at the top of the stack.
   *
   * @return the element that was at the top of this stack
   * @throws RuntimeException if the stack is empty and the implementation
   *                          does not permit popping from an empty stack
   * @implSpec Implementations must document their behavior when this method
   *           is called on an empty stack, including any specific runtime
   *           exception type that may be thrown.
   */
  T pop();

  /**
   * Returns, but does not remove, the element at the top of the stack.
   *
   * @return the element at the top of this stack
   * @throws RuntimeException if the stack is empty and the implementation
   *                          does not permit peeking at an empty stack
   * @implSpec Implementations must document their behavior when this method
   *           is called on an empty stack, including any specific runtime
   *           exception type that may be thrown, or whether a sentinel value
   *           such as {@code null} is returned.
   */
  T peek();

  /**
   * Returns {@code true} if this stack contains no elements.
   *
   * @return {@code true} if this stack is empty; {@code false} otherwise
   */
  boolean isEmpty();

  /**
   * Returns the number of elements currently in the stack.
   *
   * @return the size of this stack, which is always a non-negative integer
   */
  int size();
}
