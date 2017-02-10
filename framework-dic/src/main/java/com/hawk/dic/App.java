package com.hawk.dic;

import com.hawk.dic.data.type.DataType.EnumDataType;

public class App {

	public static void main(String[] args) {
		EnumDataType t = EnumDataType.parse(10);
		System.out.println(t.getValue());
	}

}
