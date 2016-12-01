package com.hawk.utility;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.jndi.toolkit.ctx.AtomicContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private AtomicInteger atomicInteger;
	private AtomicBoolean atomicBoolean;
	private AtomicReference<Object> atomicReference;
	private AtomicLong atomicLong;
	
	
	private AtomicIntegerArray atomicIntegerArray;
	private AtomicLongArray atomicLongArray;
	private AtomicReferenceArray<Object> atomicReferenceArray;
	
	private AtomicReferenceFieldUpdater<Object, Object> update 
		= AtomicReferenceFieldUpdater.newUpdater(Object.class, Object.class, "");
	
	public void x(){
//		atomicInteger.compareAndSet(expect, update)
//		update.compareAndSet(obj, expect, update);
		Object.class.getFields();//返回该类及其父类的公有字段
		Object.class.getDeclaredFields(); //返回该类的所有字段，不包括父类的。
		//represented by this Class object. This includes public, protected, default (package) access, and private fields, but excludes inherited fields. 

	}
	
    public static void main( String[] args ) throws InterruptedException, ClassNotFoundException
    {
    	
    	
    	
        System.out.println( "Hello World!" );
        
//        ReentrantLock lock = new ReentrantLock();
//        
//        lock.tryLock();
//        lock.newCondition();
//        lock.unlock();
//        Thread.sleep(1000*60);
        
        class Super {
        	
        }
        
        class Sub extends Super{
        	
        }
        
        System.out.println(Super.class.isAssignableFrom(Sub.class));
        
        NoClassDefFoundError x;
    }
}
