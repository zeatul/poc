package com.hawk.utility.example.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁就是在同一时刻只能有一个线程获取到锁，而其他获取锁的线程只能处于同步队列中等待，只有获取锁的线程释放了锁，后继的线程才能够获取锁
 * 不支持锁重入
 * @author pzhang1
 *
 */
public class Mutex implements Lock {

	private static class Sync extends AbstractQueuedSynchronizer{
		
		/**
		 * 是否处于占用状态
		 */
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		/**
		 * 当状态为0的时候获取锁
		 */
		@Override
		protected boolean tryAcquire(int acquires) {
			if (compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		/**
		 * 释放锁，将状态设置为0
		 */
		@Override
		protected boolean tryRelease(int releases) {
			if (getState() == 0)
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		/**
		 * 返回一个condition,每个condition都包含了一个condition队列
		 * @return
		 */
		Condition newCondtion(){
			return new ConditionObject();
		}
		
	}


	/**
	 * 
	 */
	private final Sync sync = new Sync();


	@Override
	public void lock() {
		sync.acquire(1);	
	}
	
	


	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}


	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}


	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}


	@Override
	public void unlock() {
		sync.release(1);
	}


	@Override
	public Condition newCondition() {
		return sync.newCondtion();
	}
	
	
}
