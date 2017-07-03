package com.hawk.ecom.product.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.PRODUCT+0, "产品目录未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+1, "产品目录已经存在,[产品目录编号唯一],[产品目录名称+父产品目录主键,唯一]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+2, "类型是最终分类的产品目录，不允许创建下级产品目录");
		errorMap.put(ErrorCodeAllocation.PRODUCT+3, "产品目录含有下级产品目录，不允许删除");
		errorMap.put(ErrorCodeAllocation.PRODUCT+4, "类型是不是最终分类的产品目录，不允许用来生成实际商品");
		errorMap.put(ErrorCodeAllocation.PRODUCT+5, "产品目录状态不符合当前操作的要求");
		errorMap.put(ErrorCodeAllocation.PRODUCT+6, "产品已经存在,商店编号和产品编号组合唯一");
		errorMap.put(ErrorCodeAllocation.PRODUCT+7, "产品未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+8, "产品状态不符合当前操作的要求");
		errorMap.put(ErrorCodeAllocation.PRODUCT+9, "SkU已经存在，[同一个商户下的Sku编号唯一] ，[同一个产品的Sku属性集合组合唯一]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+10, "用户只能管理自己商铺的物品");
		errorMap.put(ErrorCodeAllocation.PRODUCT+11, "产品SKU不存在");
		errorMap.put(ErrorCodeAllocation.PRODUCT+12, "产品SKU状态不符合当前操作的要求");
		errorMap.put(ErrorCodeAllocation.PRODUCT+13, "产品SKU不符合上架条件,Sku销售价格必须大于0");
		errorMap.put(ErrorCodeAllocation.PRODUCT+14, "产品不符合上架条件,必须有处于上架状态的产品SKU,上架开始时间不能小于上架结束时间，上架结束时间必须大于当前时间");
		errorMap.put(ErrorCodeAllocation.PRODUCT+15, "产品目录 变式状态不符合当前操作的要求,[新建商品时，变式状态应该为可用状态],[维护模板时，变式状态为编辑状态]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+16, "属性名已经存在,[最终产品目录分类+属性名名称+父属性名ID+父属性值ID,唯一]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+17, "属性名未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+18, "属性名已经使用,不能删除,不能修改属性值类型和属性名业务类型");
		errorMap.put(ErrorCodeAllocation.PRODUCT+19, "属性名状态不符合当前操作要求");
		errorMap.put(ErrorCodeAllocation.PRODUCT+20, "属性值已经存在,[属性名主键+属性值,唯一]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+21, "属性值不存在");
		errorMap.put(ErrorCodeAllocation.PRODUCT+22, "属性值已经被使用,不能删除");
		errorMap.put(ErrorCodeAllocation.PRODUCT+23, "产品目录已经被使用,[属性,品牌,产品,供应商],不能删除");
		errorMap.put(ErrorCodeAllocation.PRODUCT+24, "操作的产品目录的父目录必须一致");
		errorMap.put(ErrorCodeAllocation.PRODUCT+25, "当前操作对象的关联对象的产品分类目录必须一致");
		errorMap.put(ErrorCodeAllocation.PRODUCT+26, "属性名的PID必须和属性值的属性名ID一致");
		errorMap.put(ErrorCodeAllocation.PRODUCT+27, "产品定义关联的属性名ID必须唯一,[包括关键属性，非关键属性和sku属性]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+28, "属性未被产品引用");
		errorMap.put(ErrorCodeAllocation.PRODUCT+29, "SKU属性未被产品定义");
		errorMap.put(ErrorCodeAllocation.PRODUCT+30, "缺少SKU属性,[比产品定义里的少]");
		errorMap.put(ErrorCodeAllocation.PRODUCT+31, "产品SKU用到的属性名和产品定义里的sku属性名不完全一致");
		errorMap.put(ErrorCodeAllocation.PRODUCT+32, "产品SKU更新库存量遇到并发冲突，请重试");
		errorMap.put(ErrorCodeAllocation.PRODUCT+33, "库存信息未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+34, "图片未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+35, "属性值未被引用");
		errorMap.put(ErrorCodeAllocation.PRODUCT+36, "属性值已经被引用");
	
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
