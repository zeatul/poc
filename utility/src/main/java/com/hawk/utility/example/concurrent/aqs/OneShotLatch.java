package com.hawk.utility.example.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * java并发编程实践示例
 * 清单 14.14 二元闭锁使用AbstractQueuedSynchronizer
 * @author pzhang1
 *
 */
public class OneShotLatch {
	
	private final Sync sync = new Sync();
	
	public void singnal(){sync.releaseShared(0);}
	
	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}

	
	private class Sync extends AbstractQueuedSynchronizer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6087184627602338434L;

		@Override
		protected int tryAcquireShared(int igonred) {
			
			return (getState() == 1) ? 1 : -1;
		}
		
		@Override
		protected boolean tryReleaseShared(int ignored) {
			setState(1);
			return true;
		}
	}
}
