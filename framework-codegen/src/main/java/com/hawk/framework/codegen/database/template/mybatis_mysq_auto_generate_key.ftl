<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">

	<!-- 禁止系统使用自增主键 -->
	
	<insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="${className}">
		INSERT INTO ${tableName} (			
			<#list fields as field>
				<#if field.columnName != "id">
					${field.columnName}<#if field_index < columnsLength-1 >,</#if>
				</#if>				
			</#list>
		)		
		VALUES (	
		<#list fields as field>
			<#if field.columnName != "id">
				${r"#"}{${field.fieldName}}<#if field_index < columnsLength-1 >,</#if>
			</#if>
		</#list>	
		) 
	</insert>
	
</mapper>