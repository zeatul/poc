<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">

	<sql id="columns">
		<#list fields as field>
		${field.columnName} as "${field.fieldName}" <#if field_has_next>,</#if>
		</#list>
	</sql>
	
	<sql id="where">
		<trim prefix="WHERE" prefixOverrides="AND">		 
		<#list fields as field>		   
			<if test="${field.fieldName} != null"> AND ${field.columnName} = ${r"#"}{${field.fieldName}}  </if>
		</#list>
		</trim>	
	</sql>
	
	<sql id="where_old">
		<trim prefix="WHERE" prefixOverrides="AND">
		<#list fields as field>
			AND ${field.columnName} = ${r"#"}{old_${field.fieldName}}
		</#list>
		</trim>
	</sql>
	
	<sql id="update">
		<set>
		<#list fields as field> 
			<#if field.columnName != "id">
			${field.columnName} = ${r"#"}{${field.fieldName}},
			</#if>
		</#list>
		</set>
	</sql>
	
	<sql id="updateWithoutNull">
		<set>
		<#list fields as field> 
			<#if field.columnName != "id">
				<if test="${field.fieldName} != null">${field.columnName} = ${r"#"}{${field.fieldName}},</if>
			</#if>
		</#list>
		</set>
	</sql>
	
	<#if (key?? && key?size>0)>
	<sql id="pkwhere">
		<trim prefix="WHERE" prefixOverrides="AND">
		<#list key as field>
			AND ${field.columnName} = ${r"#"}{old_${field.fieldName}}
		</#list>
		</trim>
	</sql>
	</#if>
	
	<#if (key?? && key?size>0)>
	<select id="load"  resultType="${className}Domain">
		SELECT 
		<include refid="columns" />
		FROM ${tableName}
		<include refid="pkwhere" />		
	</select>
	</#if>
	
	<select id="load${className}Dynamic" parameterType="hashmap" resultType="${className}Domain">
		SELECT 		
		<include refid="columns" />
		FROM ${tableName}
		<include refid="where" />
		<if test="_orderby != null">ORDER BY ${r"$"}{_orderby}</if>
		<if test="_offset != null">LIMIT ${r"#"}{_offset},${r"#"}{_limit}</if>	
	</select>


	<select id="count"  parameterType="hashmap" resultType="integer">
		SELECT count(*) 
		FROM ${tableName}
		<include refid="where" />
	</select>
	
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
	
	<#if (key?? && key?size>0)>
	<delete id="delete" >
		DELETE FROM ${tableName}
		<include refid="pkwhere" />	
	#end
	</delete>
	</#if>
	
	<delete id="deleteDynamic" parameterType="hashmap">
		DELETE FROM ${tableName}
		<include refid="where" />
	</delete>

	<#if (key?? && key?size>0)>
	<update id="update${className}" parameterType="${className}">
		UPDATE ${tableName}	
		<include refid="update" />
		<include refid="pkwhere" />	
	</update>
	</#if>
	
	<#if (key?? && key?size>0)>
	<update id="update${className}WithoutNull" parameterType="${className}">
		UPDATE ${tableName}
		<include refid="updateWithoutNull"/>
		<include refid="pkwhere" />	
	</update>
	</#if>
	
	<update id="update${className}Dynamic" parameterType="hashmap">
		UPDATE ${tableName}	
		<include refid="update" />
		<include refid="where_old" />
	</update>
	
	
	
</mapper>