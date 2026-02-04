# Algo Workout (Java)

A focused Java workspace for practicing data structures and algorithms for coding interviews.

## Project Structure

```
.
├─ README.md
└─ src
   ├─ main
   │  └─ java
   │     └─ com/podopryhora/algoworkout
   │        ├─ App.java
   │        ├─ algorithms
   │        │  ├─ arrays
   │        │  ├─ backtracking
   │        │  ├─ dp
   │        │  ├─ graphs
   │        │  ├─ greedy
   │        │  ├─ math
   │        │  ├─ search
   │        │  ├─ sorting
   │        │  └─ strings
   │        └─ ds
   │           ├─ arraylist
   │           ├─ graph
   │           ├─ hash
   │           ├─ heap
   │           ├─ linkedlist
   │           ├─ stackqueue
   │           ├─ tree
   │           └─ trie
   └─ test
      └─ java
         └─ com/podopryhora/algoworkout
```

## Getting Started

Run tests with the Gradle wrapper:

```
./gradlew test
```

## Implementation Plan (Easiest → Hardest)

### Data Structures

- [ ] Arrays (custom dynamic array)
- [ ] Singly linked list
- [ ] Doubly linked list
- [ ] Stack (array + linked list)
- [ ] Queue (array + linked list)
- [ ] Deque
- [ ] Hash table (separate chaining)
- [ ] Hash table (open addressing)
- [ ] Binary heap (min/max)
- [ ] Binary search tree
- [ ] AVL tree
- [ ] Red-black tree
- [ ] Trie
- [ ] Disjoint set (union-find)
- [ ] Graph (adjacency list/matrix)

### Algorithms

- [x] Linear search
- [x] Binary search
- [ ] Two pointers patterns
- [ ] Sliding window
- [ ] Prefix sum
- [ ] Sorting: bubble, selection, insertion
- [ ] Sorting: merge, quick, heap
- [ ] Bit manipulation basics
- [ ] Recursion patterns
- [ ] Backtracking: subsets, permutations, combinations
- [ ] Greedy: interval scheduling, activity selection
- [ ] BFS / DFS
- [ ] Topological sort
- [ ] Shortest path: Dijkstra, Bellman-Ford
- [ ] Minimum spanning tree: Kruskal, Prim
- [ ] Dynamic programming: 1D (fib, house robber)
- [ ] Dynamic programming: 2D (knapsack, LCS)
- [ ] String algorithms: KMP, Rabin-Karp
- [ ] Advanced: segment tree, Fenwick tree

## Example

- `LinearSearch` lives in `src/main/java/com/podopryhora/algoworkout/algorithms/search/LinearSearch.java`
- Tests live in `src/test/java/com/podopryhora/algoworkout/algorithms/search/LinearSearchTest.java`
