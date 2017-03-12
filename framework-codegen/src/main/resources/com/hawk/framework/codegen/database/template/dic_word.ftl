<?xml version="1.0" encoding="UTF-8"?>
<words>
<#list wordList as word>
	<word>
		<id>${word.id}</id>
		<code>${word.code}</code>
		<name>${word.name}</name>
		<#if word.displayName??>
		<displayName>${word.displayName}</displayName>
		</#if>
		<#if word.objectComment??>
		<objectComment>${word.objectComment}</objectComment>
		</#if>
		<useType>${word.useType}</useType>
		<dataType>${word.dataType}</dataType>
		<#if word.dataType="string">
		<charMinLength>${word.charMinLength}</charMinLength>
		<charMaxLength>${word.charMaxLength}</charMaxLength>
		<isOnlyAscii>${word.isOnlyAscii}</isOnlyAscii>
		<#if word.regex??>
		<regex>${word.regex}</regex>
		</#if>
		</#if>
		<#if word.dataType="numeric">
		<numericPrecision>${word.numericPrecision}</numericPrecision>
		<numericScale>${word.numericScale}</numericScale>
		</#if>
		<#if word.dataType="date">
		<datetimePrecision>${word.datetimePrecision}</datetimePrecision>
		</#if>
		<#if minValue??>
		<minValue>${word.minValue}</minValue>
		</#if>
		<#if maxValue??>
		<maxValue>${word.maxValue}</maxValue>
		</#if>		
		<#if word.isEnum=0>
		<isEnum>no</isEnum>
		<#else>
		<isEnum>yes</isEnum>
		<#if enumKey??>
		<enumKey>${word.enumKey}</enumKey>
		</#if>
		<#if enumValue??>
		<enumValue>${word.enumKey}</enumValue>
		</#if>		 
		</#if>			
	</word>
</#list> 
</words>
