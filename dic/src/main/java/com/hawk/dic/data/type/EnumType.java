package com.hawk.dic.data.type;

import java.util.List;

/**
 * 枚举类型
 * @author pzhang1
 *
 * @param <T>
 */
public abstract class EnumType<T> implements DataType{

	

	public List<T> getValues() {
		return values;
	}

	public void setValues(List<T> values) {
		this.values = values;
	}

	private List<T> values ;
}
