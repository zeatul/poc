package com.hawk.framework.dic.data.type;

import com.hawk.framework.dic.data.type.DataType.EnumDataType;

public class DataTypeFactory {

	public DataType build(EnumDataType type){
		if (type == EnumDataType.Date)
			return new DateType();
		else if (type == EnumDataType.EnumInteger)
			return new EnumIntegerType();
		else if (type == EnumDataType.EnumString)
			return new EnumStringType();
		else if (type == EnumDataType.Long)
			return new LongType();
		else if (type == EnumDataType.Integer)
			return new IntegerType();
		else if (type == EnumDataType.Numeric)
			return new NumericType();
		else if (type == EnumDataType.String)
			return new StringType();
		else
			throw new RuntimeException("Unsupported enum");
	}
	
	public DataType build(int type){
		return build(EnumDataType.parse(type));
	}
}
