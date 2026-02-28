package com.podopryhora.algoworkout.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.Test;

public class FixedArrayBlockingQueueTest {

  @Test
  void constructorRejectsNonPositiveCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new FixedArrayBlockingQueue<>(0));
    assertThrows(IllegalArgumentException.class, () -> new FixedArrayBlockingQueue<>(-1));
  }

  @Test
  void takeReturnsValuesInFifoOrderAcrossThreads() throws Exception {
    FixedArrayBlockingQueue<Integer> queue = new FixedArrayBlockingQueue<>(2);

    try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
      Future<?> producer =
          executor.submit(
              () -> {
                queue.put(1);
                queue.put(2);
                return null;
              });
      Future<Integer> first = executor.submit(queue::take);
      Future<Integer> second = executor.submit(queue::take);

      producer.get(1, TimeUnit.SECONDS);
      assertEquals(1, first.get(1, TimeUnit.SECONDS));
      assertEquals(2, second.get(1, TimeUnit.SECONDS));
    }
  }

  @Test
  void wraparoundPreservesFifoOrder() throws Exception {
    FixedArrayBlockingQueue<Integer> queue = new FixedArrayBlockingQueue<>(3);

    queue.put(1);
    queue.put(2);
    queue.put(3);

    assertEquals(1, queue.take());
    assertEquals(2, queue.take());

    queue.put(4);
    queue.put(5);

    assertEquals(3, queue.take());
    assertEquals(4, queue.take());
    assertEquals(5, queue.take());
  }

  @Test
  void nullValuesAreAllowed() throws Exception {
    FixedArrayBlockingQueue<String> queue = new FixedArrayBlockingQueue<>(2);

    queue.put(null);
    queue.put("next");

    assertNull(queue.take());
    assertEquals("next", queue.take());

    queue.put("after-null");
    assertEquals("after-null", queue.take());
  }

  @Test
  void putIsInterruptedWhileWaitingForLock() throws Exception {
    FixedArrayBlockingQueue<Integer> queue = new FixedArrayBlockingQueue<>(1);
    ReentrantLock externalLock = extractLock(queue);
    CountDownLatch started = new CountDownLatch(1);
    CountDownLatch finished = new CountDownLatch(1);
    AtomicReference<Throwable> failure = new AtomicReference<>();

    externalLock.lock();
    Thread worker =
        new Thread(
            () -> {
              started.countDown();
              try {
                queue.put(1);
              } catch (Throwable throwable) {
                failure.set(throwable);
              } finally {
                finished.countDown();
              }
            });

    try {
      worker.start();
      assertTrue(started.await(1, TimeUnit.SECONDS));
      waitUntilWaitingForLock(worker);

      worker.interrupt();
      assertTrue(finished.await(1, TimeUnit.SECONDS));
      assertInstanceOf(InterruptedException.class, failure.get());
    } finally {
      externalLock.unlock();
      worker.join(1_000);
    }
  }

  @Test
  void takeIsInterruptedWhileWaitingForLock() throws Exception {
    FixedArrayBlockingQueue<Integer> queue = new FixedArrayBlockingQueue<>(1);
    ReentrantLock externalLock = extractLock(queue);
    CountDownLatch started = new CountDownLatch(1);
    CountDownLatch finished = new CountDownLatch(1);
    AtomicReference<Throwable> failure = new AtomicReference<>();

    externalLock.lock();
    Thread worker =
        new Thread(
            () -> {
              started.countDown();
              try {
                queue.take();
              } catch (Throwable throwable) {
                failure.set(throwable);
              } finally {
                finished.countDown();
              }
            });

    try {
      worker.start();
      assertTrue(started.await(1, TimeUnit.SECONDS));
      waitUntilWaitingForLock(worker);

      worker.interrupt();
      assertTrue(finished.await(1, TimeUnit.SECONDS));
      assertInstanceOf(InterruptedException.class, failure.get());
    } finally {
      externalLock.unlock();
      worker.join(1_000);
    }
  }

  @Test
  void putAndTakeRemainInterruptibleWhileWaitingOnConditions() throws Exception {
    FixedArrayBlockingQueue<Integer> fullQueue = new FixedArrayBlockingQueue<>(1);
    fullQueue.put(1);

    assertConditionWaitIsInterruptible(() -> fullQueue.put(2));
    assertConditionWaitIsInterruptible(() -> new FixedArrayBlockingQueue<Integer>(1).take());
  }

  private static ReentrantLock extractLock(FixedArrayBlockingQueue<?> queue) throws Exception {
    var field = FixedArrayBlockingQueue.class.getDeclaredField("lock");
    field.setAccessible(true);
    return (ReentrantLock) field.get(queue);
  }

  private static void waitUntilWaitingForLock(Thread thread) throws InterruptedException {
    long deadlineNanos = System.nanoTime() + TimeUnit.SECONDS.toNanos(1);
    while (System.nanoTime() < deadlineNanos) {
      Thread.State state = thread.getState();
      if (state == Thread.State.WAITING || state == Thread.State.TIMED_WAITING) {
        return;
      }
      Thread.sleep(10);
    }
    throw new AssertionError("Thread did not block on the queue lock");
  }

  private static void assertConditionWaitIsInterruptible(InterruptibleAction action)
      throws Exception {
    CountDownLatch finished = new CountDownLatch(1);
    AtomicReference<Throwable> failure = new AtomicReference<>();
    Thread worker =
        new Thread(
            () -> {
              try {
                action.run();
              } catch (Throwable throwable) {
                failure.set(throwable);
              } finally {
                finished.countDown();
              }
            });

    worker.start();
    waitUntilWaitingOnCondition(worker);
    worker.interrupt();

    assertTrue(finished.await(1, TimeUnit.SECONDS));
    assertInstanceOf(InterruptedException.class, failure.get());
    worker.join(1_000);
  }

  @FunctionalInterface
  private interface InterruptibleAction {
    void run() throws Exception;
  }

  private static void waitUntilWaitingOnCondition(Thread thread) throws InterruptedException {
    long deadlineNanos = System.nanoTime() + TimeUnit.SECONDS.toNanos(1);
    while (System.nanoTime() < deadlineNanos) {
      if (thread.getState() == Thread.State.WAITING) {
        return;
      }
      Thread.sleep(10);
    }
    throw new AssertionError("Thread did not reach condition wait");
  }
}
