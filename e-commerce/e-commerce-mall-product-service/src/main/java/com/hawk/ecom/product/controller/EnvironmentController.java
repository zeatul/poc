package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.product.request.UpdateCategoryVariantStatusParam;
import com.hawk.ecom.product.request.UpdateProductStatusParam;
import com.hawk.ecom.product.request.UpdateSkuStatusParam;
import com.hawk.ecom.product.service.AttrNameService;
import com.hawk.ecom.product.service.AttrValueService;
import com.hawk.ecom.product.service.CategoryService;
import com.hawk.ecom.product.service.ProductService;
import com.hawk.ecom.product.service.SkuService;
import com.hawk.ecom.product.service.StockService;
import com.hawk.ecom.pub.constant.ConstAttrNameCode;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

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

	@Autowired
	private ProductService productService;

	@Autowired
	private SkuService skuService;

	@Autowired
	private StockService stockService;

	private String userCode = "000002";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/init", method = { POST, GET })
	public String init(HttpServletRequest request) throws Exception {

		AuthThreadLocal.setMobileNumber("13300000000");
		AuthThreadLocal.setStoreCode("ST000001");
		AuthThreadLocal.setUserCode(userCode); // admin

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
		createCategorParam.setCategoryCode("virtual_product");
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
		createCategorParam.setCategoryCode("charge_data");
		createCategorParam.setIsLeaf(1);
		createCategorParam.setOperatorCode(userCode);
		createCategorParam.setPid(virtualGoodsCategory.getId());
		CategoryDomain mobileDataChargeCategory = categoryService.createCategory(createCategorParam);

		buildChargeDataProduct(mobileDataChargeCategory);
		buildBsiProduct(screenBrokenCategory);
		

		logger.info("Succeeded to generate all products");
	}
	
	
	public void buildBsiProduct(CategoryDomain screenBrokenCategory) {
		/**
		 * 更新变式状态为编辑状态，以创建属性名和属性值
		 */
		UpdateCategoryVariantStatusParam updateCategoryVariantStatusParam = new UpdateCategoryVariantStatusParam();
		updateCategoryVariantStatusParam.setOperatorCode(userCode);
		updateCategoryVariantStatusParam.setCategoryVariantStatus(ConstCategory.CategoryVariantStatus.EDITING);
		updateCategoryVariantStatusParam.setIds(Arrays.asList(screenBrokenCategory.getId()));
		categoryService.updateCategoryVariantStatus(updateCategoryVariantStatusParam);
		
		/**
		 * 创建产品分类碎屏险的属性名和属性值，属性名:(保险月数,保险档次)
		 */
		/*保险月数*/
		CreateAttrNameParam createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("保险月数");
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Bsi.INSURANCE_PERIOD_MONTH);
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.INTEGER);
		createAttrNameParam.setCategoryId(screenBrokenCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain insurancePeriodMonthAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> insurancePeriodMonthDomainList = new ArrayList<AttrValueDomain>();
		
		CreateAttrValueParam createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("1个月");
		createAttrValueParam.setAttrNameId(insurancePeriodMonthAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("1");
		createAttrValueParam.setAttrDisplayEnValue("1 month");
		AttrValueDomain insurePeriodMonthAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insurancePeriodMonthDomainList.add(insurePeriodMonthAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("3个月");
		createAttrValueParam.setAttrNameId(insurancePeriodMonthAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("3");
		createAttrValueParam.setAttrDisplayEnValue("3 month");
		insurePeriodMonthAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insurancePeriodMonthDomainList.add(insurePeriodMonthAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("12个月");
		createAttrValueParam.setAttrNameId(insurancePeriodMonthAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("12");
		createAttrValueParam.setAttrDisplayEnValue("12 month");
		insurePeriodMonthAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insurancePeriodMonthDomainList.add(insurePeriodMonthAttrValueDomain);
		
		/*保险档次*/
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("保险档次");
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Bsi.GRADE);
		createAttrNameParam.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.OTHERS);
		createAttrNameParam.setAttrValueType(ConstAttr.AttrValueType.INTEGER);
		createAttrNameParam.setCategoryId(screenBrokenCategory.getId());
		createAttrNameParam.setIsSearch(ConstBoolean.TRUE);
		createAttrNameParam.setPid(0);
		createAttrNameParam.setPvid(0);
		createAttrNameParam.setOperatorCode(userCode);
		AttrNameDomain insuranceGradeAttrNameDomain = attrNameService.createAttrName(createAttrNameParam);
		
		List<AttrValueDomain> insuranceGradeDomainList = new ArrayList<AttrValueDomain>();
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("碎屏保A+（联通）");
		createAttrValueParam.setAttrNameId(insuranceGradeAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("1");
		createAttrValueParam.setAttrDisplayEnValue("碎屏保A+（联通）");
		AttrValueDomain insureGradeAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insuranceGradeDomainList.add(insureGradeAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("碎屏保至尊（联通）");
		createAttrValueParam.setAttrNameId(insuranceGradeAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("2");
		createAttrValueParam.setAttrDisplayEnValue("碎屏保至尊（联通）");
		insureGradeAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insuranceGradeDomainList.add(insureGradeAttrValueDomain);
		
		createAttrValueParam = new CreateAttrValueParam();
		createAttrValueParam.setAttrDisplayValue("碎屏保至尊+（联通）");
		createAttrValueParam.setAttrNameId(insuranceGradeAttrNameDomain.getId());
		createAttrValueParam.setOperatorCode(userCode);
		createAttrValueParam.setAttrValue("3");
		createAttrValueParam.setAttrDisplayEnValue("碎屏保至尊+（联通）");
		insureGradeAttrValueDomain = attrValueService.createAttrValue(createAttrValueParam);
		insuranceGradeDomainList.add(insureGradeAttrValueDomain);
		
		/**
		 * 更新变式状态为可用状态，以创建产品
		 */
		updateCategoryVariantStatusParam = new UpdateCategoryVariantStatusParam();
		updateCategoryVariantStatusParam.setOperatorCode(userCode);
		updateCategoryVariantStatusParam.setCategoryVariantStatus(ConstCategory.CategoryVariantStatus.AVAILABLE);
		updateCategoryVariantStatusParam.setIds(Arrays.asList(screenBrokenCategory.getId()));
		categoryService.updateCategoryVariantStatus(updateCategoryVariantStatusParam);

		
		/*构造产品*/
		for (AttrValueDomain insurancePeriodMonthDomain: insurancePeriodMonthDomainList ){
			for (AttrValueDomain insuranceGradeDomain:insuranceGradeDomainList){
				/**
				 * 产品
				 */
				CreateProductParam createProductParam = new CreateProductParam();

				createProductParam.setCategoryId(screenBrokenCategory.getId());
				createProductParam.setIsVirtual(ConstBoolean.TRUE);
				createProductParam.setDeliveryType(ConstProduct.DeliveryType.BSI);
				createProductParam.setOperatorCode(userCode);
				createProductParam.setProductCode(null);
				createProductParam.setProductDesc(null);
				createProductParam.setProductHomePage("http://www.baidu.com");
				List<Integer> productKeyAttrValueIds = Arrays.asList(insurancePeriodMonthDomain.getId(), insuranceGradeDomain.getId());
				createProductParam.setProductKeyAttrValueIds(productKeyAttrValueIds);
				createProductParam.setProductMemo("测试");
				String productName = StringTools.concat(insurancePeriodMonthDomain.getAttrDisplayValue(), insuranceGradeDomain.getAttrDisplayValue());
				createProductParam.setProductName(productName);

				createProductParam.setProductNormalAttrValueIds(null);
				String thumbnailHead = StringTools.concatWithSymbol("-", insurancePeriodMonthDomain.getAttrDisplayEnValue(),insuranceGradeDomain.getAttrDisplayEnValue());
				String thumbnail = StringTools.concatWithSymbol(".", thumbnailHead, "jpg");
				createProductParam.setThumbnail(thumbnail);
				createProductParam.setProductSkuAttrNameIds(null);
				ProductDomain productDomain = productService.createProduct(createProductParam);

				/**
				 * 产品SKU
				 */
				CreateSkuParam createSkuParam = new CreateSkuParam();
				createSkuParam.setCurrency(ConstProduct.Currency.RMB);
				createSkuParam.setDepth(0);
				createSkuParam.setWidth(0);
				createSkuParam.setLengthUnit(ConstProduct.LengthUnit.MILLIMETER);
				createSkuParam.setMarketPrice(new BigDecimal(100));
				createSkuParam.setOperatorCode(userCode);
				createSkuParam.setProductId(productDomain.getId());
				createSkuParam.setSalePrice(new BigDecimal("0.01"));
				createSkuParam.setSkuAttrValueIds(null);
				createSkuParam.setSkuCode(null);
				createSkuParam.setSkuMemo(null);
				createSkuParam.setSkuName(productDomain.getProductName());
				createSkuParam.setThumbnail(productDomain.getThumbnail());
				createSkuParam.setWeight(0);
				createSkuParam.setWeightUnit(ConstProduct.WeightUnit.GRAM);
				createSkuParam.setWidth(0);
				createSkuParam.setHeight(0);
				createSkuParam.setSkuExtra1("1200");
				SkuDomain skuDomain = skuService.createSku(createSkuParam);

				/**
				 * 库存
				 */
				CreateStockParam createStockParam = new CreateStockParam();
				createStockParam.setOperatorCode(userCode);
				createStockParam.setSkuId(skuDomain.getId());
				createStockParam.setStockItemCode(thumbnailHead);
				createStockParam.setStockMemo("测试");
				createStockParam.setStockOperation(ConstProduct.StockOperation.STOCK_IN);
				createStockParam.setStockQuantity(999999);
				createStockParam.setWarehouseCode("warehouse1");
				stockService.createStock(createStockParam);

				/**
				 * sku上架
				 */
				UpdateSkuStatusParam updateSkuStatusParam = new UpdateSkuStatusParam();
				updateSkuStatusParam.setIds(Arrays.asList(skuDomain.getId()));
				updateSkuStatusParam.setOperatorCode(userCode);
				updateSkuStatusParam.setSkuStatus(ConstProduct.SkuStatus.ON_SALE);
				skuService.updateSkuStatus(updateSkuStatusParam);

				/**
				 * product上架
				 */
				UpdateProductStatusParam updateProductStatusParam = new UpdateProductStatusParam();
				updateProductStatusParam.setIds(Arrays.asList(productDomain.getId()));
				Date onSaleStdt = new Date();
				Date onSaleEndt = DateTools.addDays(onSaleStdt, 365);
				updateProductStatusParam.setOnSaleEndt(onSaleEndt);
				updateProductStatusParam.setOnSaleStdt(onSaleStdt);
				updateProductStatusParam.setOperatorCode(userCode);
				updateProductStatusParam.setProductStatus(ConstProduct.ProductStatus.ON_SALE);
				productService.updateProductStatus(updateProductStatusParam);

				logger.info("Succeeded to generate product : {}", productDomain.getProductName());

			}
		}
	}

	public void buildChargeDataProduct(CategoryDomain mobileDataChargeCategory) {

		/**
		 * 更新变式状态为编辑状态，以创建属性名和属性值
		 */
		UpdateCategoryVariantStatusParam updateCategoryVariantStatusParam = new UpdateCategoryVariantStatusParam();
		updateCategoryVariantStatusParam.setOperatorCode(userCode);
		updateCategoryVariantStatusParam.setCategoryVariantStatus(ConstCategory.CategoryVariantStatus.EDITING);
		updateCategoryVariantStatusParam.setIds(Arrays.asList(mobileDataChargeCategory.getId()));
		categoryService.updateCategoryVariantStatus(updateCategoryVariantStatusParam);

		/**
		 * 创建产品分类流量充值的属性名和属性值 , 属性名：移动服务商(电信,移动,联通)
		 */
		CreateAttrNameParam createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrName("移动服务商");
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Mobile.OPERATOR);
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
		 * 创建产品分类流量充值的属性名和属性值 ,属性名：流量大小(100M,1000M,5000M)
		 */
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Mobile.DATA_SIZE);
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
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Mobile.PROVINCE);
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
		 * 创建产品分类流量充值的属性名和属性值 , 属性名：流量类型(本省,省漫)
		 */
		createAttrNameParam = new CreateAttrNameParam();
		createAttrNameParam.setAttrNameCode(ConstAttrNameCode.Mobile.REGION_TYPE);
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
		 * 更新变式状态为可用状态，以创建产品
		 */
		updateCategoryVariantStatusParam = new UpdateCategoryVariantStatusParam();
		updateCategoryVariantStatusParam.setOperatorCode(userCode);
		updateCategoryVariantStatusParam.setCategoryVariantStatus(ConstCategory.CategoryVariantStatus.AVAILABLE);
		updateCategoryVariantStatusParam.setIds(Arrays.asList(mobileDataChargeCategory.getId()));
		categoryService.updateCategoryVariantStatus(updateCategoryVariantStatusParam);

		/**
		 * 创建产品,产品Sku,产品库存 运营商，流量大小，流量类型,地区全部为关键属性.
		 */
		// List<ProductDomain> productDomainList = new
		// ArrayList<ProductDomain>();
		for (AttrValueDomain supplierDomain : supplierDomainList) {
			for (AttrValueDomain chargeQuantityDomain : chargeQuantityDomainList) {
				for (AttrValueDomain districtDomain : districtDomainList) {
					for (AttrValueDomain mobildDataDomain : mobileDataTypeDomainList) {

						/**
						 * 产品
						 */
						CreateProductParam createProductParam = new CreateProductParam();

						createProductParam.setCategoryId(mobileDataChargeCategory.getId());
						createProductParam.setIsVirtual(ConstBoolean.TRUE);
						createProductParam.setDeliveryType(ConstProduct.DeliveryType.CHARGE_FLOW_DATA);
						createProductParam.setOperatorCode(userCode);
						createProductParam.setProductCode(null);
						createProductParam.setProductDesc(null);
						createProductParam.setProductHomePage("http://www.baidu.com");
						List<Integer> productKeyAttrValueIds = Arrays.asList(supplierDomain.getId(), chargeQuantityDomain.getId(), districtDomain.getId(),
								mobildDataDomain.getId());
						createProductParam.setProductKeyAttrValueIds(productKeyAttrValueIds);
						createProductParam.setProductMemo("测试");
						String productName = StringTools.concat("流量充值", supplierDomain.getAttrDisplayValue(), chargeQuantityDomain.getAttrDisplayValue(),
								districtDomain.getAttrDisplayValue(), mobildDataDomain.getAttrDisplayValue());
						createProductParam.setProductName(productName);

						createProductParam.setProductNormalAttrValueIds(null);
						String thumbnailHead = StringTools.concatWithSymbol("-", supplierDomain.getAttrDisplayEnValue(),
								chargeQuantityDomain.getAttrDisplayEnValue(), districtDomain.getAttrDisplayEnValue(), mobildDataDomain.getAttrDisplayEnValue());
						String thumbnail = StringTools.concatWithSymbol(".", thumbnailHead, "jpg");
						createProductParam.setThumbnail(thumbnail);
						createProductParam.setProductSkuAttrNameIds(null);
						ProductDomain productDomain = productService.createProduct(createProductParam);

						/**
						 * 产品SKU
						 */
						CreateSkuParam createSkuParam = new CreateSkuParam();
						createSkuParam.setCurrency(ConstProduct.Currency.RMB);
						createSkuParam.setDepth(0);
						createSkuParam.setWidth(0);
						createSkuParam.setLengthUnit(ConstProduct.LengthUnit.MILLIMETER);
						createSkuParam.setMarketPrice(new BigDecimal(100));
						createSkuParam.setOperatorCode(userCode);
						createSkuParam.setProductId(productDomain.getId());
						createSkuParam.setSalePrice(new BigDecimal("0.01"));
						createSkuParam.setSkuAttrValueIds(null);
						createSkuParam.setSkuCode(null);
						createSkuParam.setSkuMemo(null);
						createSkuParam.setSkuName(productDomain.getProductName());
						createSkuParam.setThumbnail(productDomain.getThumbnail());
						createSkuParam.setWeight(0);
						createSkuParam.setWeightUnit(ConstProduct.WeightUnit.GRAM);
						createSkuParam.setWidth(0);
						createSkuParam.setHeight(0);
						SkuDomain skuDomain = skuService.createSku(createSkuParam);

						/**
						 * 库存
						 */
						CreateStockParam createStockParam = new CreateStockParam();
						createStockParam.setOperatorCode(userCode);
						createStockParam.setSkuId(skuDomain.getId());
						createStockParam.setStockItemCode(thumbnailHead);
						createStockParam.setStockMemo("测试");
						createStockParam.setStockOperation(ConstProduct.StockOperation.STOCK_IN);
						createStockParam.setStockQuantity(999999);
						createStockParam.setWarehouseCode("warehouse1");
						stockService.createStock(createStockParam);

						/**
						 * sku上架
						 */
						UpdateSkuStatusParam updateSkuStatusParam = new UpdateSkuStatusParam();
						updateSkuStatusParam.setIds(Arrays.asList(skuDomain.getId()));
						updateSkuStatusParam.setOperatorCode(userCode);
						updateSkuStatusParam.setSkuStatus(ConstProduct.SkuStatus.ON_SALE);
						skuService.updateSkuStatus(updateSkuStatusParam);

						/**
						 * product上架
						 */
						UpdateProductStatusParam updateProductStatusParam = new UpdateProductStatusParam();
						updateProductStatusParam.setIds(Arrays.asList(productDomain.getId()));
						Date onSaleStdt = new Date();
						Date onSaleEndt = DateTools.addDays(onSaleStdt, 365);
						updateProductStatusParam.setOnSaleEndt(onSaleEndt);
						updateProductStatusParam.setOnSaleStdt(onSaleStdt);
						updateProductStatusParam.setOperatorCode(userCode);
						updateProductStatusParam.setProductStatus(ConstProduct.ProductStatus.ON_SALE);
						productService.updateProductStatus(updateProductStatusParam);

						logger.info("Succeeded to generate product : {}", productDomain.getProductName());

					}
				}
			}
		}
	}

}
