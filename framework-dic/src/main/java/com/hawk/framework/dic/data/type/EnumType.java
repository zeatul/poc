package com.hawk.framework.dic.data.type;

import java.util.List;

/**
 * 枚举类型
 * 
 * @author pzhang1
 *
 * @param <T>
 */
public abstract class EnumType<K, V> implements DataType {

	public List<Pair<K, V>> getValues() {
		return values;
	}

	public void setValues(List<Pair<K, V>> values) {
		this.values = values;
	}

	public static class Pair<K, V> {
		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		private K key;
		private V value;
	}

	public List<Pair<K,V>> values;
}
