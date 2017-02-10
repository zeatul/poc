package ${packageName}.mapper;
import java.util.List;
import java.util.Map;
import ${packageName}.domain.${className}Domain;
import org.apache.ibatis.annotations.Param;

/**
 * ${tableName}
 * ${tableComment}
 * 
 * @author Gen
 */
public interface ${className}Mapper  {

	<#if (keyList?? && keyList?size>0)>
	${className}Domain load(<#list key as field>@Param("${field.fieldName}")${field.filedType} ${field.fieldName}<#if field_has_next>,</#if> </#list>);
	</#if>
	
	List<${className}Domain> loadDynamic(Map<String,Object> params);
	
	/**
	 * 需要传入分页参数
	 */
	List<${className}Domain> loadDynamicPaging(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(${className}Domain ${className?uncap_first}Domain);
	
	<#if (keyList?? && keyList?size>0)>
	int delete(<#list keyList as field>@Param("${field.fieldName}")${field.filedType} ${field.fieldName}<#if field_has_next>,</#if> </#list>);
	</#if>
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(${className}Domain ${className?uncap_first}Domain);
	
	int updateWithoutNull(${className}Domain ${className?uncap_first}Domain);
	
	int update(Map<String,Object> params);
	
	


}