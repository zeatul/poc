package com.hawk.framework.pub.constant;

public class ConstBoolean {

	public final static int TRUE = 1;

	public final static int FALSE = 0;

	public static boolean parse(Integer value) {
		if (value == null)
			return false;

		if (value <= 0)

			return false;

		return true;
	}

}
