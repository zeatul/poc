package ${packageName}.domain;
import java.io.Serializable;
<#if (importList?? && importList?size>0) >
<#list importList as importItem>
import ${importItem};
</#list>
</#if>




/**
 * ${desc}
 * The class is mapped to the table ${tableCode} 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ${className}Domain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	<#list fieldList as field>
	/**
	 * ${field.fieldDesc} ${field.columnCode}
	 */
	private ${field.fieldType} ${field.fieldName};
	
	</#list>
	
	<#list fieldList as field>
	/**
	 * 
	 * @return ${field.fieldDesc} ${field.columnCode}
	 */
	public ${field.fieldType} get${field.fieldName?cap_first}(){
		return ${field.fieldName};
	}
	
	/**
	 * 
	 * @param ${field.fieldName} ${field.fieldDesc} ${field.columnCode}
	 */	
	public void set${field.fieldName?cap_first} (${field.fieldType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	
	</#list>


}
