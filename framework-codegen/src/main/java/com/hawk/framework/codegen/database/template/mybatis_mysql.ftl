<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">

	<#include "mybatis_base.ftl">
	
	<select id="loadDynamicPaging" parameterType="hashmap" resultType="${className}Domain">
		SELECT 		
		<include refid="columns" />
		FROM ${tableName}
		<include refid="where" />
		<if test="_orderby != null">ORDER BY ${r"$"}{_orderby}</if>
		<if test="_offset != null">LIMIT ${r"#"}{_offset},${r"#"}{_limit}</if>	
	</select>
</mapper>