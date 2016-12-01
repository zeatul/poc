package com.hawk.utility.example.singleton;

/**
 * 双重加锁机制构造单例。DCL = Double Checked Locking
 * @author pzhang1
 *
 */
public class CreateSingletonFromDCL {
	
	private static volatile CreateSingletonFromDCL createSingletonFromDCL = null;
	
	private CreateSingletonFromDCL(){
		
	}
	
	public static CreateSingletonFromDCL getInstance(){
		if (createSingletonFromDCL == null){
			synchronized (CreateSingletonFromDCL.class) {
				if (createSingletonFromDCL == null)
					createSingletonFromDCL = new CreateSingletonFromDCL();
			}
		}
		
		return createSingletonFromDCL;
	}

}
