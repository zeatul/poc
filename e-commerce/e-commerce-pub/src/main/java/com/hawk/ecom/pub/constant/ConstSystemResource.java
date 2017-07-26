package com.hawk.ecom.pub.constant;

public class ConstSystemResource {
	
	/**
	 * 资源节点状态
	 * @author Administrator
	 *
	 */
	public static interface NodeStatus{
		public final int EDITING = 1;
		/**
		 * 禁用
		 */
		public final int FORBIDDEN = 99;
		/**
		 * 启用
		 */
		public final int ACTIVATED = 100;
	}
	
	/**
	 * 资源节点类型
	 * @author Administrator
	 *
	 */
	public static interface NodeType{
		/**
		 * 菜单
		 */
		public final int MENU = 10;
	}
	
	/**
	 * 资源节点子类型
	 * @author Administrator
	 *
	 */
	public static interface NodeSubType{
		/**
		 * 其它
		 */
		public final int OTHER = 100;
	}

	/**
	 * 资源值类型
	 * @author Administrator
	 *
	 */
	public static interface NodeValueType{
		/**
		 * HTTP
		 */
		public final int HTTP = 10;
	}
}
