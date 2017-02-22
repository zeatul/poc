<?xml version="1.0" encoding="UTF-8"?>
<table>
	<code>${code}</code>
	<#if name??>
	<name>${name}</name>
	</#if>
	<#if comment??>
	<comment>${comment}</comment>
	</#if>
	<type>${type}</type>
	<columns>
		<#list columnList as column>
		<column>
			<objectId>${column.objectId}</objectId>
			<dataDefinition>
				<objectId>${column.dataDefinition.objectId}</objectId>
			</dataDefinition>
			<#if column.code??>
			<code>${column.code}</code>
			</#if>
			<#if column.name??>
			<name>${column.name}</name>
			</#if>
			<#if column.comment??>
			<comment>${column.comment}</comment>
			</#if>
			<#if column.nullable=0>
			<nullable>no</nullable>
			<#else>
			<nullable>yes</nullable>
			</#if>
			<#if column.isPk=0>
			<isPk>no</isPk>
			<#else>
			<isPk>yes</isPk>
			</#if>
		</column>
		</#list>
	</columns>
</table>
