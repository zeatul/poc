package com.hawk.utility.example.jvm;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * InvokeDynamic指令 ，它与其它invoke指令的最大区别就是：它的分配逻辑不是由虚拟机决定的，而是由程序员决定的。
 * @author pzhang1
 *
 */
public class InvokeDynamicTest {
	
	public static void main(String[] args) throws Throwable{
		INDY_BootstrapMethod().invokeExact("icyfenix");
	}
	
	
	public static void testMethod(String s){
		System.out.println("Hello String:"+s);
	}
	
	public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name ,MethodType mt) throws  Throwable{
		return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
	}
	
	private static MethodType MT_BootstrapMethod(){
		return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
	}
	
	private static MethodHandle MH_BootstrapMethod() throws Throwable {
		return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
	}
	
	private static MethodHandle INDY_BootstrapMethod() throws Throwable{
		CallSite cs = (CallSite)MH_BootstrapMethod().invokeWithArguments(lookup(),"testMethod",	MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V",null));
		return cs.dynamicInvoker();
	}

}
