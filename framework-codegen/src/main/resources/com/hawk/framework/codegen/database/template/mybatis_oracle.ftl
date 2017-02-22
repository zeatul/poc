<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">

	<#include "mybatis_base.html">	
	
	<select id="loadDynamicPaging" parameterType="hashmap" resultType="${className}Domain">
		SELECT 		
		<include refid="columns" />
		FROM { 
			SELECT <choose>
				<when test="_orderby != null">
					ROW_NUMBER() OVER (ORDER BY ${r"$"}{_orderby}) AS "row_paging_no"
				</when>
				<otherwise>
					ROWNUM AS "row_paging_no"
				</otherwise>
				,${tableCode}.*
			</choose>
			FROM ${tableCode}
			<include refid="where" />
		}
		<if test="_maxNo != null">row_paging_no &lt; ${r"#"}{_maxNo}</if>
		<if test="_minNo != null">row_paging_no &gt; ${r"#"}{_minNo}</if>
	</select>
</mapper>