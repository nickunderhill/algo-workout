package com.podopryhora.algoworkout.ds;

import java.util.Arrays;
import java.util.Objects;

/**
 * Hash table map based on an array of buckets where collisions are resolved with separate chaining.
 *
 * <p>Main idea: compute key hash, map it to a bucket index, then traverse that bucket chain to
 * find, insert, update, or remove an entry.
 *
 * <p>Expected performance (with good hash distribution and controlled load factor): put/get/remove
 * are O(1) average, O(n) worst case.
 *
 * <p>Space complexity: O(n) for stored entries plus bucket array overhead.
 */
public class HashMap<K, V> {

  protected static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final int MAXIMUM_CAPACITY = 1 << 30;
  protected static final float DEFAULT_LOAD_FACTOR = 0.75f;

  protected Node<K, V>[] table;
  protected int size;
  protected int threshold;
  protected float loadFactor;

  /**
   * Associates the specified value with the specified key.
   *
   * <p>Time complexity: O(1) average, O(n) worst case. Space complexity: O(1) amortized.
   *
   * @param key key with which the specified value is associated
   * @param value value to be associated with the specified key
   * @return previous value associated with key, or null if there was no mapping
   */
  public V put(K key, V value) {
    // Lazily initialize storage on first insert.
    if (table == null || table.length == 0) {
      int initialCapacity = DEFAULT_INITIAL_CAPACITY;
      @SuppressWarnings("unchecked")
      Node<K, V>[] newTable = (Node<K, V>[]) new Node[initialCapacity];
      table = newTable;
      if (loadFactor <= 0.0f) {
        loadFactor = DEFAULT_LOAD_FACTOR;
      }
      threshold = (int) (initialCapacity * loadFactor);
    }

    // Compute bucket position for the incoming key.
    int hash = hash(key);
    int index = indexFor(hash, table.length);

    // Update value when the key already exists in the chain.
    Node<K, V> current = table[index];
    while (current != null) {
      if (current.hash == hash && Objects.equals(current.key, key)) {
        V oldValue = current.value;
        current.value = value;
        return oldValue;
      }
      current = current.next;
    }

    // Prepend a new node when key is not present.
    Node<K, V> node = new Node<>();
    node.hash = hash;
    node.key = key;
    node.value = value;
    node.next = table[index];
    table[index] = node;
    size++;

    // Grow table when load factor threshold is exceeded.
    if (size > threshold) {
      resize();
    }
    return null;
  }

  /**
   * Returns the value to which the specified key is mapped.
   *
   * <p>Time complexity: O(1) average, O(n) worst case. Space complexity: O(1).
   *
   * @param key key whose associated value is to be returned
   * @return value mapped to key, or null if no mapping exists
   */
  public V get(Object key) {
    // Empty map has no stored mappings.
    if (table == null || table.length == 0) {
      return null;
    }

    int hash = hash(key);
    int index = indexFor(hash, table.length);
    Node<K, V> node = table[index];
    // Traverse the bucket chain to match key and hash.
    while (node != null) {
      if (node.hash == hash && Objects.equals(node.key, key)) {
        return node.value;
      }
      node = node.next;
    }
    return null;
  }

  /**
   * Removes the mapping for a key from this map if present.
   *
   * <p>Time complexity: O(1) average, O(n) worst case. Space complexity: O(1).
   *
   * @param key key whose mapping is to be removed
   * @return previous value associated with key, or null if no mapping existed
   */
  public V remove(Object key) {
    // Empty map cannot contain the requested key.
    if (table == null || table.length == 0) {
      return null;
    }

    // Locate the bucket for this key hash.
    int hash = hash(key);
    int index = indexFor(hash, table.length);

    // Track both current and previous nodes to relink on deletion.
    Node<K, V> previous = null;
    Node<K, V> current = table[index];

    // Walk the chain to find the exact key match.
    while (current != null) {
      if (current.hash == hash && Objects.equals(current.key, key)) {
        // Removing head requires updating the bucket pointer.
        if (previous == null) {
          table[index] = current.next;
        } else {
          // Removing middle/tail bypasses the current node.
          previous.next = current.next;
        }
        size--;
        return current.value;
      }
      previous = current;
      current = current.next;
    }
    return null;
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   *
   * <p>Time complexity: O(1) average, O(n) worst case. Space complexity: O(1).
   *
   * @param key key whose presence is to be tested
   * @return true if this map contains a mapping for key
   */
  public boolean containsKey(Object key) {
    // Empty map has no keys.
    if (table == null || table.length == 0) {
      return false;
    }

    // Find the bucket and scan the collision chain for an exact key match.
    int hash = hash(key);
    int index = indexFor(hash, table.length);
    Node<K, V> current = table[index];
    while (current != null) {
      if (current.hash == hash && Objects.equals(current.key, key)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /**
   * Removes all mappings from this map.
   *
   * <p>Time complexity: O(n). Space complexity: O(1) auxiliary.
   */
  public void clear() {
    if (table == null || table.length == 0) {
      size = 0;
      return;
    }
    // Clear all bucket heads, which drops entire chains for GC.
    Arrays.fill(table, null);
    // Reset element count.
    size = 0;
  }

  /**
   * Returns the number of key-value mappings in this map.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return number of stored entries
   */
  public int size() {
    return size;
  }

  /**
   * Returns true if this map contains no key-value mappings.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return true if this map is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the current bucket array capacity.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @return number of buckets in the table
   */
  public int capacity() {
    return table == null ? 0 : table.length;
  }

  /**
   * Computes the mixed hash value used for bucket placement.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @param key key to hash
   * @return mixed hash value
   */
  protected int hash(Object key) {
    // null key is supported and mapped with hash 0.
    if (key == null) {
      return 0;
    }
    // Spread high bits into low bits to improve bucket distribution.
    int h = key.hashCode();
    return h ^ (h >>> 16);
  }

  /**
   * Converts hash to bucket index for the given table length.
   *
   * <p>Time complexity: O(1). Space complexity: O(1).
   *
   * @param hash mixed hash value
   * @param tableLength current table length
   * @return bucket index in range [0, tableLength)
   */
  protected int indexFor(int hash, int tableLength) {
    return hash & (tableLength - 1);
  }

  /**
   * Resizes the table and rehashes entries when threshold is exceeded.
   *
   * <p>Time complexity: O(n). Space complexity: O(n) during resize.
   */
  protected void resize() {
    // Initialize table if resize is called before first allocation.
    if (table == null || table.length == 0) {
      int initialCapacity = DEFAULT_INITIAL_CAPACITY;
      @SuppressWarnings("unchecked")
      Node<K, V>[] newTable = (Node<K, V>[]) new Node[initialCapacity];
      table = newTable;
      if (loadFactor <= 0.0f) {
        loadFactor = DEFAULT_LOAD_FACTOR;
      }
      threshold = (int) (initialCapacity * loadFactor);
      return;
    }

    int oldCapacity = table.length;
    if (oldCapacity >= MAXIMUM_CAPACITY) {
      threshold = Integer.MAX_VALUE;
      return;
    }

    int newCapacity = oldCapacity << 1;
    if (newCapacity > MAXIMUM_CAPACITY) {
      newCapacity = MAXIMUM_CAPACITY;
    }

    @SuppressWarnings("unchecked")
    Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

    // Rehash every node into the new bucket array.
    for (int i = 0; i < oldCapacity; i++) {
      Node<K, V> node = table[i];
      while (node != null) {
        Node<K, V> next = node.next;
        int newIndex = indexFor(node.hash, newCapacity);
        node.next = newTable[newIndex];
        newTable[newIndex] = node;
        node = next;
      }
    }

    table = newTable;
    threshold = (int) (newCapacity * loadFactor);
  }

  /** Entry node for separate chaining inside a bucket. */
  protected static class Node<K, V> {
    protected int hash;
    protected K key;
    protected V value;
    protected Node<K, V> next;
  }
}
