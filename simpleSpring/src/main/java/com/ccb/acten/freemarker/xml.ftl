<?xml version="1.0" encoding="UTF-8"?>
<ROOT>
<#list keys as key>
	<#if key != "class">
	<${key}>${values[key]}</${key}>
	</#if>
</#list>
</ROOT>