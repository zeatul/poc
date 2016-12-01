package com.hawk.utility.example.concurrent.atom;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {

	private static class Node<E>{
		final E item;
		final AtomicReference<Node<E>> next;
		public Node(E item ,Node<E> next){
			this.item = item;
			this.next = new AtomicReference<Node<E>>(next);
		}
	}
	
	private final Node<E> dummy = new Node<E>(null,null);
	private final AtomicReference<Node<E>> head = new AtomicReference<LinkedQueue.Node<E>>(dummy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<LinkedQueue.Node<E>>(dummy);
	
	public boolean put(E item){
		Node<E> newNode = new Node<E>(item,null);
		while(true){
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			if (curTail == tail.get()){
				if(tailNext != null){
					//队列处于中间状态，推进尾节点
					tail.compareAndSet(curTail, tailNext);
				}else{
					if (curTail.next.compareAndSet(null, newNode)){
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}
	
}
