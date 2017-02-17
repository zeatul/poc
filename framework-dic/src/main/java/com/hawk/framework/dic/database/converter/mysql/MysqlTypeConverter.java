package com.hawk.framework.dic.database.converter.mysql;

import com.hawk.framework.dic.data.type.DataType;
import com.hawk.framework.dic.data.type.DateType;
import com.hawk.framework.dic.data.type.EnumIntegerType;
import com.hawk.framework.dic.data.type.EnumStringType;
import com.hawk.framework.dic.data.type.IntegerType;
import com.hawk.framework.dic.data.type.LongType;
import com.hawk.framework.dic.data.type.NumericType;
import com.hawk.framework.dic.data.type.StringType;
import com.hawk.framework.dic.database.converter.TypeConverter;
import com.hawk.framework.utility.StringTools;

/**
 * 将框架的数据类型 转换成Mysql 数据库类型
 * @author pzhang1
 *
 */
public class MysqlTypeConverter implements TypeConverter{

	@Override
	public String convert(DataType dataType) {
		if (dataType instanceof StringType){
			StringType s = (StringType)(dataType);
			return StringTools.concat("varchar(",s.getMaxLength(),")");
		}else if (dataType instanceof LongType){
			return "bigint(20)";
		}else if (dataType instanceof IntegerType){
			return "integer";
		}else if(dataType instanceof EnumStringType){
			EnumStringType e = (EnumStringType)(dataType);
			return StringTools.concat("varchar(",e.getMaxLength(),")");
		}else if (dataType instanceof EnumIntegerType){
			return "integer";
		}else if (dataType instanceof NumericType){
			NumericType n = (NumericType)dataType; 
			return StringTools.concat("decimal(",n.getPrecision(),",",n.getScale(),")");
		}else if (dataType instanceof DateType){
			return "timestamp(3) null";
		}else{
			throw new RuntimeException("UnSupported DataType");
		}
			
	}

}
