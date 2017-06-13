package com.hawk.ecom.muser.service;

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

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.exception.MallRoleNotFoundRuntimeException;
import com.hawk.ecom.muser.exception.MallUserNotLoginRuntimeException;
import com.hawk.ecom.muser.persist.domain.MallRoleDomain;
import com.hawk.ecom.muser.persist.domain.MallRoleUserDomain;
import com.hawk.ecom.muser.persist.domain.MallUserDomain;
import com.hawk.ecom.muser.persist.mapper.MallRoleMapper;
import com.hawk.ecom.muser.persist.mapper.MallRoleUserMapper;
import com.hawk.ecom.muser.persist.mapperex.MallRoleUserExMapper;
import com.hawk.ecom.muser.request.MallAddRoleMembersParam;
import com.hawk.ecom.muser.request.MallListRoleMembersParam;
import com.hawk.ecom.muser.request.MallRemoveRoleMembersParam;
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
	private MallAuthService authService;

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
	public void addRoleMembers(@NotNull("参数") @Valid MallAddRoleMembersParam mallAddRoleMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		String operator = mallAddRoleMembersParam.getOperatorCode();
		String roleCode = mallAddRoleMembersParam.getRoleCode();
		MallRoleDomain mallRoleDomain = queryRoleByRoleCode(roleCode);
		if (mallRoleDomain == null)
			throw new MallRoleNotFoundRuntimeException();
		if (roleCode.equals("superadmin")){
			throw new RuntimeException("superadmin角色不能加入任何成员");
		}
		for (String userCode : mallAddRoleMembersParam.getUserCodes()) {
			if ("superadmin".equals(userCode) ){
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
	public void removeRoleMembers(@NotNull("参数") @Valid MallRemoveRoleMembersParam mallRemoveRoleMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		String operator = mallRemoveRoleMembersParam.getOperatorCode();
		String roleCode = mallRemoveRoleMembersParam.getRoleCode();
		MallRoleDomain mallRoleDomain = queryRoleByRoleCode(roleCode);
		if (mallRoleDomain == null)
			throw new MallRoleNotFoundRuntimeException();
		if (roleCode.equals("superadmin")){
			throw new RuntimeException("superadmin角色不能移除任何成员");
		}
		for (String userCode : mallRemoveRoleMembersParam.getUserCodes()) {
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
	public List<MallUserDomain> listRoleMembers(@NotNull("参数") @Valid MallListRoleMembersParam mallListRoleMembersParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin", "admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(mallListRoleMembersParam.getOrder())){
			mallListRoleMembersParam.setOrder("c.create_date desc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam().put("roleCode", "admin"), mallListRoleMembersParam);

		return mallRoleUserExMapper.listMembersInRole(params);
	}
}
