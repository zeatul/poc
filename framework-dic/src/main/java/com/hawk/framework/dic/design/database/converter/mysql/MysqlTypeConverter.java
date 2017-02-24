package com.hawk.framework.dic.design.database.converter.mysql;

import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.database.converter.TypeConverter;
import com.hawk.framework.utility.StringTools;

/**
 * 将框架的数据类型 转换成Mysql 数据库类型
 * @author pzhang1
 *
 */
public class MysqlTypeConverter implements TypeConverter{

	

	@Override
	public String convert(DataDefinition dataDefinition) {
		EnumDataType dataType = dataDefinition.getDataType();
		if (dataType == EnumDataType.String){
			return StringTools.concat("varchar(",dataDefinition.getCharMaxLength(),")");
		}else if (dataType == EnumDataType.Long){
			return "bigint(20)";
		}else if (dataType == EnumDataType.Integer){
			return "integer";
		}else if(dataType == EnumDataType.Numeric){
			return StringTools.concat("decimal(",dataDefinition.getNumericPrecision(),",",dataDefinition.getNumericScale(),")");
		}else if (dataType == EnumDataType.Date){
			return "timestamp("+dataDefinition.getDatetimePrecision()+") null";
		}else{
			throw new RuntimeException("UnSupported DataType");
		}
	}

}
