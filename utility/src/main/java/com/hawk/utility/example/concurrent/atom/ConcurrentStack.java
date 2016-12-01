package com.hawk.utility.example.concurrent.atom;

import java.util.concurrent.atomic.AtomicReference;

/**
 * java并发编程实践 清单15.6 使用Treiber算法的非阻塞栈
 * 
 * @author pzhang1
 *
 * @param <E>
 */
public class ConcurrentStack<E> {

	AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

	public void push(E item) {
		Node<E> newHead = new Node<E>(item);
		Node<E> oleHead;
		do {
			oleHead = top.get();
			newHead.next = oleHead;
		} while (!top.compareAndSet(oleHead, newHead));
	}

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = top.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}

	private static class Node<E> {
		public final E item;
		public Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
