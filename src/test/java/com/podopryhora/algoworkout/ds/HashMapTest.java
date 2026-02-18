package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class HashMapTest {

  @Test
  void putAndGetOnSingleKeyWorks() {
    HashMap<String, Integer> map = new HashMap<>();

    assertNull(map.put("a", 1));
    assertEquals(1, map.get("a"));
    assertEquals(1, map.size());
  }

  @Test
  void putOnExistingKeyReturnsOldValueAndKeepsSize() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("a", 1);

    assertEquals(1, map.put("a", 2));
    assertEquals(2, map.get("a"));
    assertEquals(1, map.size());
  }

  @Test
  void containsKeyDistinguishesNullValueFromMissingKey() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("present", null);

    assertTrue(map.containsKey("present"));
    assertNull(map.get("present"));
    assertFalse(map.containsKey("missing"));
  }

  @Test
  void nullKeyIsSupportedAcrossPutGetContainsAndRemove() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put(null, 7);
    map.put("x", 1);

    assertEquals(7, map.get(null));
    assertTrue(map.containsKey(null));
    assertEquals(7, map.remove(null));
    assertNull(map.get(null));
    assertFalse(map.containsKey(null));
    assertEquals(1, map.size());
  }

  @Test
  void getReturnsAllValuesFromCollisionChain() {
    HashMap<CollisionKey, Integer> map = new HashMap<>();
    CollisionKey first = new CollisionKey("first");
    CollisionKey second = new CollisionKey("second");
    CollisionKey third = new CollisionKey("third");

    map.put(first, 1);
    map.put(second, 2);
    map.put(third, 3);

    assertEquals(1, map.get(first));
    assertEquals(2, map.get(second));
    assertEquals(3, map.get(third));
    assertNull(map.get(new CollisionKey("missing")));
  }

  @Test
  void removeHeadOfCollisionChainKeepsOtherEntriesAccessible() {
    HashMap<CollisionKey, Integer> map = new HashMap<>();
    CollisionKey first = new CollisionKey("first");
    CollisionKey second = new CollisionKey("second");

    map.put(first, 1);
    map.put(second, 2);

    assertEquals(2, map.remove(second));
    assertEquals(1, map.get(first));
    assertNull(map.get(second));
    assertEquals(1, map.size());
  }

  @Test
  void removeMiddleOfCollisionChainKeepsNeighborsLinked() {
    HashMap<CollisionKey, Integer> map = new HashMap<>();
    CollisionKey first = new CollisionKey("first");
    CollisionKey second = new CollisionKey("second");
    CollisionKey third = new CollisionKey("third");

    map.put(first, 1);
    map.put(second, 2);
    map.put(third, 3);

    assertEquals(2, map.remove(second));
    assertEquals(1, map.get(first));
    assertEquals(3, map.get(third));
    assertNull(map.get(second));
    assertEquals(2, map.size());
  }

  @Test
  void removeTailOfCollisionChainKeepsOtherEntriesAccessible() {
    HashMap<CollisionKey, Integer> map = new HashMap<>();
    CollisionKey first = new CollisionKey("first");
    CollisionKey second = new CollisionKey("second");
    CollisionKey third = new CollisionKey("third");

    map.put(first, 1);
    map.put(second, 2);
    map.put(third, 3);

    assertEquals(1, map.remove(first));
    assertEquals(2, map.get(second));
    assertEquals(3, map.get(third));
    assertNull(map.get(first));
    assertEquals(2, map.size());
  }

  @Test
  void removeMissingKeyReturnsNullAndDoesNotChangeSize() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("a", 1);

    assertNull(map.remove("missing"));
    assertEquals(1, map.size());
    assertEquals(1, map.get("a"));
  }

  @Test
  void clearRemovesAllMappingsAndResetsSize() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("a", 1);
    map.put("b", 2);

    map.clear();

    assertEquals(0, map.size());
    assertTrue(map.isEmpty());
    assertNull(map.get("a"));
    assertNull(map.get("b"));
    assertFalse(map.containsKey("a"));
  }

  @Test
  void resizePreservesAllMappings() {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 40; i++) {
      map.put(i, i * 10);
    }

    assertTrue(map.capacity() >= 64);
    assertEquals(40, map.size());
    for (int i = 0; i < 40; i++) {
      assertEquals(i * 10, map.get(i));
    }
  }

  @Test
  void initialAndPostPutCapacityMatchLazyAllocation() {
    HashMap<String, Integer> map = new HashMap<>();

    assertEquals(0, map.capacity());
    map.put("a", 1);
    assertEquals(16, map.capacity());
  }

  private static final class CollisionKey {
    private final String id;

    private CollisionKey(String id) {
      this.id = id;
    }

    @Override
    public int hashCode() {
      return 42;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof CollisionKey other)) {
        return false;
      }
      return Objects.equals(id, other.id);
    }
  }
}
