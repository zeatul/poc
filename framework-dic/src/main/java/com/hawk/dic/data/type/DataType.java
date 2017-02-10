package com.hawk.dic.data.type;

/**
 * 数据类型标记接口
 * @author pzhang1
 *
 */
public interface DataType {
	
	public EnumDataType getType();
	
	public static enum EnumDataType{
		
		Integer(10),
		Long(20),
		Number(30),
		String(40),
		Date(50),
		EnumString(60),
		EnumInteger(70)		
		;
		
		public int getValue() {
			return value;
		}

		private int value;
		
		private EnumDataType(int value){
			this.value = value;
		}
		
		public static EnumDataType parse(int type){
			EnumDataType[] types =  EnumDataType.values();
			for (EnumDataType t : types){
				if (t.getValue() == type)
					return t;
			}
			throw new RuntimeException("Unsupported type");
		}
	}
}
