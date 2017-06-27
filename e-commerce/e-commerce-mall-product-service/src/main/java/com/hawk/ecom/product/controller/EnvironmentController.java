package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.service.AttrNameService;
import com.hawk.ecom.product.service.AttrValueService;
import com.hawk.ecom.product.service.CategoryService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/mall/admin/product/env/")
@CrossOrigin
public class EnvironmentController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttrNameService attrNameService;
	
	@Autowired
	private AttrValueService attrValueService;
	
	private String userCode = "000002";

	@RequestMapping(value = "/init", method = { POST, GET })
	public String init(HttpServletRequest request) throws Exception {

		AuthThreadLocal.setMobileNumber("13300000000");
		AuthThreadLocal.setStoreCode("ST000001");
		AuthThreadLocal.setUserCode(userCode); //admin
		
		build();

		return "Succeeded to init the product environment" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);

	}

	private void build() {
		/**
		 * 创建产品分类：虚拟产品->(流量充值,碎屏险)
		 * 
		 */
		CreateCategoryParam createCategorParam = new CreateCategoryParam();
		createCategorParam.setCategoryName("虚拟商品");
		createCategorParam.setIsLeaf(0);
		createCategorParam.setOperatorCode(userCode);
		createCategorParam.setPid(0);
		
		CategoryDomain virtualGoodsCategory = categoryService.createCategory(createCategorParam);
		
		createCategorParam = new CreateCategoryParam();
		createCategorParam.setCategoryName("碎屏险");
		createCategorParam.setIsLeaf(1);
		createCategorParam.setOperatorCode(userCode);
		createCategorParam.setPid(virtualGoodsCategory.getId());
		
		CategoryDomain screenBrokenCategory = categoryService.createCategory(createCategorParam);
		
		createCategorParam = new CreateCategoryParam();
		createCategorParam.setCategoryName("流量充值");
		createCategorParam.setIsLeaf(1);
		createCategorParam.setOperatorCode(userCode);
		createCategorParam.setPid(virtualGoodsCategory.getId());
		
		CategoryDomain mobileDataChargeCategory = categoryService.createCategory(createCategorParam);
		
		
		/**
		 * 创建产品分类流量充值的属性名和属性值 , 属性名：移动服务商(电信,移动,联通)
		 */
		CreateAttrNameParam createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("移动服务商");
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.STRING);
		createAttrNameParam.setCategoryId(mobileDataChargeCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain mobileSupplierAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> supplierDomainList = new ArrayList<AttrValueDomain>();
		
		CreateAttrValueParam createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("电信");
		createAttrValueParam.setAttrNameId(mobileSupplierAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("telecom");
		createAttrValueParam.setAttrDisplayEnValue("telecom");
		AttrValueDomain telecomSupplierAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		supplierDomainList.add(telecomSupplierAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("联通");
		createAttrValueParam.setAttrNameId(mobileSupplierAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("unicom");
		createAttrValueParam.setAttrDisplayEnValue("uniom");
		AttrValueDomain unicomSupplierAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		supplierDomainList.add(unicomSupplierAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("移动");
		createAttrValueParam.setAttrNameId(mobileSupplierAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("mobile");
		createAttrValueParam.setAttrDisplayEnValue("mobile");
		AttrValueDomain mobileSupplierAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		supplierDomainList.add(mobileSupplierAttrValueDomain);
		
		/**
		 * 创建产品分类流量充值的属性名和属性值   ,属性名：流量大小(100M,1000M,5000M)
		 */
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("流量大小");
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.INTEGER);
		createAttrNameParam.setCategoryId(mobileDataChargeCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain chargeQuantityAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> chargeQuantityDomainList = new ArrayList<AttrValueDomain>();
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("100M");
		createAttrValueParam.setAttrNameId(chargeQuantityAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("100");
		createAttrValueParam.setAttrDisplayEnValue("100M");
		AttrValueDomain charge100MAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		chargeQuantityDomainList.add(charge100MAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("1000M");
		createAttrValueParam.setAttrNameId(chargeQuantityAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("1000");
		createAttrValueParam.setAttrDisplayEnValue("1000M");
		AttrValueDomain charge1000MAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		chargeQuantityDomainList.add(charge1000MAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("5000M");
		createAttrValueParam.setAttrNameId(chargeQuantityAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("5000");
		createAttrValueParam.setAttrDisplayEnValue("5000M");
		AttrValueDomain charge5000MAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		chargeQuantityDomainList.add(charge5000MAttrValueDomain);
		
		/**
		 * 创建产品分类流量充值的属性名和属性值 , 属性名:地区(江苏,上海,广东)
		 */
		
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("地区");
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.STRING);
		createAttrNameParam.setCategoryId(mobileDataChargeCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain districtAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> districtDomainList = new ArrayList<AttrValueDomain>();
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("江苏");
		createAttrValueParam.setAttrNameId(districtAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("320000");
		createAttrValueParam.setAttrDisplayEnValue("jiangsu");
		AttrValueDomain jiangsuDistrictAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		districtDomainList.add(jiangsuDistrictAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("上海");
		createAttrValueParam.setAttrNameId(districtAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("310000");
		createAttrValueParam.setAttrDisplayEnValue("shanghai");
		AttrValueDomain shanghaiDistrictAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		districtDomainList.add(shanghaiDistrictAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("广东");
		createAttrValueParam.setAttrNameId(districtAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("440000");
		createAttrValueParam.setAttrDisplayEnValue("guangdong");
		AttrValueDomain guangdongDistrictAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		districtDomainList.add(guangdongDistrictAttrValueDomain);
		
		/**
		 * 创建产品分类流量充值的属性名和属性值 , 属性名：流量类型(全国,本地)
		 */
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("流量类型");
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.STRING);
		createAttrNameParam.setCategoryId(mobileDataChargeCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain mobileDataTypeAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> mobileDataTypeDomainList = new ArrayList<AttrValueDomain>();
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("本地使用");
		createAttrValueParam.setAttrNameId(mobileDataTypeAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("province");
		createAttrValueParam.setAttrDisplayEnValue("province");
		AttrValueDomain provinceDataTypeAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		mobileDataTypeDomainList.add(provinceDataTypeAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("全国通用");
		createAttrValueParam.setAttrNameId(mobileDataTypeAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("country");
		createAttrValueParam.setAttrDisplayEnValue("country");
		AttrValueDomain countryDataTypeAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		mobileDataTypeDomainList.add(countryDataTypeAttrValueDomain);
		
		/**
		 * 创建产品,产品Sku,产品库存
		 * 运营商，流量大小，流量类型,地区全部为关键属性
		 */
		for(AttrValueDomain supplierDomain : supplierDomainList){
			for(AttrValueDomain chargeQuantityDomain : chargeQuantityDomainList){
				for(AttrValueDomain districtDomain : districtDomainList){
					for(AttrValueDomain mobildDataDomain : mobileDataTypeDomainList){
						CreateProductParam createProductParam = new CreateProductParam();
						
						createProductParam.setCategoryId(mobileDataChargeCategory.getId());
						createProductParam.setIsVirtual(ConstBoolean.TRUE);
					}
				}
			}
		}
	}

}
