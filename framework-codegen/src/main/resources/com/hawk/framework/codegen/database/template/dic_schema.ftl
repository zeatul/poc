<?xml version="1.0" encoding="UTF-8"?>
<schema>
	<id>${id}</id>
	<code>${code}</code>
	<#if name??>
	<name>${name}</name>
	</#if>
	<#if comment??>
	<comment>${comment}</comment>
	</#if>
	<#if (applicationList?? && applicationList?size>0)>
	<applications>
	<#list applicationList as application>
		<application>
			<id>${application.id}</id>
		</application>
	</#list>
	</applications>
	</#if>
</schema>
