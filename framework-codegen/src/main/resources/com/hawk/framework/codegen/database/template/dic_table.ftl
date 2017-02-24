<?xml version="1.0" encoding="UTF-8"?>
<table>
	<id>${id}</id>
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
			<id>${column.id}</id>
			<dataDefinition>
				<id>${column.dataDefinition.id}</id>
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
	<#if (indexList?? && indexList?size>0)>
	<indexes>
	<#list indexList as index>
		<index>
			<id>${index.id}</id>
			<code>${index.code}</code>
			<#if index.name??>
			<code>${index.name}</code>
			</#if>
			<#if index.comment??>
			<comment>${index.comment}</comment>
			</#if>
			<columns>
			<#list index.columnList as column>
				<column>
					<id>${column.id}</id>
				</column>
			</#list>
			</columns>
		</index>
	</#list>
	<indexes>
	</#if>
</table>
