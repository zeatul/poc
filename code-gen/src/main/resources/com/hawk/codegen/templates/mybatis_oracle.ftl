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
	
	<select id="load${className}" parameterType="integer" resultType="${className}">
		SELECT 
		<include refid="columns" />
		FROM ${tableName}
		WHERE 
		<#list key as field>
		${field.columnName} = ${r"#"}{${field.fieldName}} <#if field_has_next> and </#if>
		</#list>	
	</select>
	
	<select id="load${className}Dynamic" parameterType="hashmap" resultType="${className}">
		SELECT 		
		<include refid="columns" />
		FROM ${tableName}
		<include refid="where" />
		<if test="_orderby != null">ORDER BY ${r"$"}{_orderby}</if>
		<if test="_offset != null">LIMIT ${r"#"}{_offset},${r"#"}{_limit}</if>	
	</select>


	<select id="count${className}Dynamic"  parameterType="hashmap" resultType="integer">
		SELECT count(*) 
		FROM ${tableName}
		<include refid="where" />
	</select>
	
	<insert id="insert${className}" useGeneratedKeys="false"  parameterType="${className}">
		INSERT INTO ${tableName} (			
			<#list fields as field>
				${field.columnName}<#if field_index < columnsLength-1 >,</#if>			
			</#list>
		)		
		VALUES (	
		<#list fields as field>
			${r"#"}{${field.fieldName}}<#if field_index < columnsLength-1 >,</#if>
		</#list>	
		) 
	</insert>
	
	<delete id="delete${className}" parameterType="integer">
		DELETE FROM ${tableName}
		WHERE  
		<#list key as field>
		${field.columnName} = ${r"#"}{${field.fieldName}} <#if field_has_next> and </#if>
		</#list>
	#end
	</delete>
	
	<delete id="delete${className}Dynamic" parameterType="hashmap">
		DELETE FROM ${tableName}
		<include refid="where" />
	</delete>

	<update id="update${className}" parameterType="${className}">
		UPDATE ${tableName}	
		<include refid="update" />
		WHERE 
		<#list key as field>
		${field.columnName} = ${r"#"}{${field.fieldName}} <#if field_has_next> and </#if>
		</#list>
	</update>
	
	<update id="update${className}Dynamic" parameterType="hashmap">
		UPDATE ${tableName}	
		<include refid="update" />
		<include refid="where_old" />
	</update>
	
	<update id="update${className}WithoutNull" parameterType="${className}">
		UPDATE ${tableName}
		<include refid="updateWithoutNull"/>
		WHERE id = ${r"#"}{id}
	</update>
	
</mapper>