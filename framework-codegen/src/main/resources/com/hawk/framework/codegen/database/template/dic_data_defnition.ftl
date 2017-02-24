<?xml version="1.0" encoding="UTF-8"?>
<dataDefinitions>
<#list dataDefnitionList as def>
	<dataDefinition>
		<id>${def.id}</id>
		<code>${def.code}</code>
		<name>${def.name}</name>
		<#if def.displayName??>
		<displayName>${def.displayName}</displayName>
		</#if>
		<#if def.objectComment??>
		<objectComment>${def.objectComment}</objectComment>
		</#if>
		<useType>${def.useType}</useType>
		<dataType>${def.dataType}</dataType>
		<#if def.dataType="string">
		<charMinLength>${def.charMinLength}</charMinLength>
		<charMaxLength>${def.charMaxLength}</charMaxLength>
		<#if def.regex??>
		<regex>${def.regex}</regex>
		</#if>
		</#if>
		<#if def.dataType="numeric">
		<numericPrecision>${def.numericPrecision}</numericPrecision>
		<numericScale>${def.numericScale}</numericScale>
		</#if>
		<#if def.dataType="date">
		<datetimePrecision>${def.datetimePrecision}</datetimePrecision>
		</#if>
		<#if minValue??>
		<minValue>${def.minValue}</minValue>
		</#if>
		<#if maxValue??>
		<maxValue>${def.maxValue}</maxValue>
		</#if>		
		<#if def.isEnum=0>
		<isEnum>no</isEnum>
		<#else>
		<isEnum>yes</isEnum>
		<#if enumKey??>
		<enumKey>${def.enumKey}</enumKey>
		</#if>
		<#if enumValue??>
		<enumValue>${def.enumKey}</enumValue>
		</#if>		 
		</#if>			
	</dataDefinition>
</#list> 
</dataDefinitions>
