<?xml version="1.0" encoding="UTF-8"?>
<application>
	<id>${id}</id>
	<code>${code}</code>
	<#if name??>
	<name>${name}</name>
	</#if>
	<#if comment??>
	<comment>${comment}</comment>
	</#if>
	<#if (tableList?? && tableList?size>0)>
	<tables>
	<#list tableList as table>
		<table>
			<id>${table.id}</id>
		</table>
	</#list>
	</tables>
	</#if>
</application>
