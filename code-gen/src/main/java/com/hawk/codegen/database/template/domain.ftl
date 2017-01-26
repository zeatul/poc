package ${packageName}.domain;
import java.io.Serializable;
<#if (importList?? && importList?size>0) >
<#list importList as importItem>
import ${importItem};
</#list>
</#if>




/**
 * ${desc}
 * The class is mapped to the table ${tableName}
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ${className}Domain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	<#list fieldList as field>
	/*${field.desc} ${field.columnName}*/
	private ${field.filedType} ${field.fieldName};
	
	</#list>
	
	<#list fieldList as field>
	/**
	 * 
	 * @return ${field.desc} ${field.columnName}
	 */
	public ${field.filedType} get${field.fieldName?cap_first}(){
		return ${field.fieldName};
	}
	
	/**
	 * 
	 * @param ${field.fieldName} ${field.desc} ${field.columnName}
	 */	
	public void set${field.fieldName?cap_first} (${field.filedType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	
	</#list>


}
