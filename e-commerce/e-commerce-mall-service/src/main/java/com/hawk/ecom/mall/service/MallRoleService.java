package com.hawk.ecom.mall.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.mall.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.mall.exception.MallRoleNotFoundRuntimeException;
import com.hawk.ecom.mall.exception.MallUserNotLoginRuntimeException;
import com.hawk.ecom.mall.persist.domain.MallRoleDomain;
import com.hawk.ecom.mall.persist.domain.MallRoleUserDomain;
import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.persist.mapper.MallRoleMapper;
import com.hawk.ecom.mall.persist.mapper.MallRoleUserMapper;
import com.hawk.ecom.mall.persist.mapperex.MallRoleUserExMapper;
import com.hawk.ecom.mall.request.MallAddAdminMembersParam;
import com.hawk.ecom.mall.request.MallListAdminMembersParam;
import com.hawk.ecom.mall.request.MallRemoveAdminMembersParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MallRoleService {

	@Autowired
	private AuthService authService;

	@Autowired
	private MallUserService mallUserService;

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	@Autowired
	private MallRoleMapper mallRoleMapper;

	@Autowired
	private MallRoleUserMapper mallRoleUserMapper;
	
	@Autowired
	private MallRoleUserExMapper mallRoleUserExMapper;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public MallRoleDomain queryRoleByRoleCode(String roleCode) {
		if (StringTools.isNullOrEmpty(roleCode))
			return null;
		MybatisParam params = new MybatisParam().put("roleCode", roleCode);
		return MybatisTools.single(mallRoleMapper.loadDynamic(params));
	}
	
	public MallRoleUserDomain queryRoleUserByRoleIdAndUserId(long roleId,long userId){
		MybatisParam params = new MybatisParam().put("roleId", roleId).put("userId", userId);
		return MybatisTools.single(mallRoleUserMapper.loadDynamic(params));
	}

	@Valid
	@Transactional
	public void addAdminMembers(@NotNull("参数") @Valid MallAddAdminMembersParam mallAddAdminMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		String operator = mallAddAdminMembersParam.getOperatorCode();
		String roleCode = "admin";
		MallRoleDomain mallRoleDomain = queryRoleByRoleCode(roleCode);
		if (mallRoleDomain == null)
			throw new MallRoleNotFoundRuntimeException();
		for (String userCode : mallAddAdminMembersParam.getUserCodes()) {
			if ("superadmin".equals(userCode)){
				/**
				 * superadmin只能创建用户和设置管理员
				 */
				throw new RuntimeException("superadmin不能加入任何角色");
			}
			MallUserDomain mallUserDomain = mallUserService.queryMallUserByUserCode(userCode);
			if (mallUserDomain == null)
				throw new MallUserNotLoginRuntimeException();
			Date curDate = new Date();
			MallRoleUserDomain mallRoleUserDomain = new MallRoleUserDomain();
			mallRoleUserDomain.setCreateDate(curDate);
			mallRoleUserDomain.setCreateUserCode(operator);
			mallRoleUserDomain.setId(pkGenService.genPk());
			mallRoleUserDomain.setRoleId(mallRoleDomain.getId());
			mallRoleUserDomain.setUpdateDate(curDate);
			mallRoleUserDomain.setUpdateUserCode(operator);
			mallRoleUserDomain.setUserId(mallUserDomain.getId());

			try {
				logger.info("add role user ,roleCode = {},userCode={}", roleCode, userCode);
				mallRoleUserMapper.insert(mallRoleUserDomain);
			} catch (DuplicateKeyException e) {
				logger.error("Found duplicate mallroleUser", e);
			}
		}

	}

	@Valid
	@Transactional
	public void removeAdminMembers(@NotNull("参数") @Valid MallRemoveAdminMembersParam mallRemoveAdminMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		String operator = mallRemoveAdminMembersParam.getOperatorCode();
		String roleCode = "admin";
		MallRoleDomain mallRoleDomain = queryRoleByRoleCode(roleCode);
		if (mallRoleDomain == null)
			throw new MallRoleNotFoundRuntimeException();
		
		for (String userCode : mallRemoveAdminMembersParam.getUserCodes()) {
			MallUserDomain mallUserDomain = mallUserService.queryMallUserByUserCode(userCode);
			if (mallUserDomain == null)
				throw new MallUserNotLoginRuntimeException();
			
			MallRoleUserDomain mallRoleUserDomain =  queryRoleUserByRoleIdAndUserId(mallRoleDomain.getId(),mallUserDomain.getId());
			if (mallRoleUserDomain!=null){
				logger.info("operator={} remove roleUser ,roleCode={},userCode={}",operator,roleCode,userCode);
				mallRoleUserMapper.delete(mallRoleUserDomain.getId());
			}
		}
	}

	@Valid
	public List<MallUserDomain> listAdminMembers(@NotNull("参数") @Valid MallListAdminMembersParam mallListAdminMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(mallListAdminMembersParam.getOrder())){
			mallListAdminMembersParam.setOrder("c.create_date desc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam().put("roleCode", "admin"), mallListAdminMembersParam);

		return mallRoleUserExMapper.listMembersInRole(params);
	}
}
