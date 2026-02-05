package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DoublyLinkedListTest {

    @Test
    void addFirstAddsSingleElementToEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(10);

        assertEquals(10, list.removeFirst());
        assertNull(list.removeFirst());
        assertNull(list.removeLast());
    }

    @Test
    void addLastAddsSingleElementToEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);

        assertEquals(10, list.removeLast());
        assertNull(list.removeLast());
        assertNull(list.removeFirst());
    }

    @Test
    void addFirstMaintainsOrder() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(3, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(1, list.removeFirst());
        assertNull(list.removeFirst());
    }

    @Test
    void addLastMaintainsOrder() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(1, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(3, list.removeFirst());
        assertNull(list.removeFirst());
    }

    @Test
    void insertAfterInMiddleKeepsLinks() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(3);

        DoublyLinkedList<Integer>.Node node = list.find(1);
        list.insertAfter(node, 2);

        assertEquals(1, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(3, list.removeFirst());
        assertNull(list.removeFirst());
    }

    @Test
    void insertAfterTailUpdatesTail() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        DoublyLinkedList<Integer>.Node node = list.find(2);
        list.insertAfter(node, 3);

        assertEquals(3, list.removeLast());
        assertEquals(2, list.removeLast());
        assertEquals(1, list.removeLast());
        assertNull(list.removeLast());
    }

    @Test
    void removeMiddleNodeRelinksNeighbors() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3, 4);

        DoublyLinkedList<Integer>.Node node = list.find(2);
        list.remove(node);

        assertEquals(1, list.removeFirst());
        assertEquals(3, list.removeFirst());
        assertEquals(4, list.removeFirst());
        assertNull(list.removeFirst());
    }

    @Test
    void removeHeadNodeUpdatesHead() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3);

        DoublyLinkedList<Integer>.Node node = list.find(1);
        list.remove(node);

        assertEquals(2, list.removeFirst());
        assertEquals(3, list.removeFirst());
        assertNull(list.removeFirst());
    }

    @Test
    void removeTailNodeUpdatesTail() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3);

        DoublyLinkedList<Integer>.Node node = list.find(3);
        list.remove(node);

        assertEquals(2, list.removeLast());
        assertEquals(1, list.removeLast());
        assertNull(list.removeLast());
    }

    @Test
    void getByIndexTraversesFromHead() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3, 4, 5);

        assertEquals(2, list.get(1).getValue());
    }

    @Test
    void getByIndexTraversesFromTail() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3, 4, 5);

        assertEquals(5, list.get(4).getValue());
    }

    @Test
    void getByIndexThrowsForNegativeIndex() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void getByIndexThrowsForTooLargeIndex() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void findReturnsNullWhenMissing() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 3);

        assertNull(list.find(4));
    }

    @Test
    void findHandlesNullValues() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast(null);
        list.addLast("a");

        DoublyLinkedList<String>.Node node = list.find(null);

        assertNotNull(node);
        assertNull(node.getValue());
    }

    @Test
    void findReturnsFirstMatch() {
        DoublyLinkedList<Integer> list = buildList(1, 2, 1);

        DoublyLinkedList<Integer>.Node node = list.find(1);
        list.remove(node);

        assertEquals(2, list.removeFirst());
        assertEquals(1, list.removeFirst());
        assertNull(list.removeFirst());
    }

    private DoublyLinkedList<Integer> buildList(Integer... values) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (Integer value : values) {
            list.addLast(value);
        }
        return list;
    }
}
