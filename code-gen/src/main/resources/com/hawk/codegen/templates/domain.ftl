package ${packageName}.domain;
import java.io.Serializable;
<#if hasDate??>import java.util.Date;</#if>



/**
 * ${tableName}
 * ${tableComment}
 * 
 * @author Code-Gen
 */
public class ${className}Domain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	<#list fields as field>
	/*${field.columnComment}*/
	private ${field.filedType} ${field.fieldName};
	
	</#list>
	
	<#list fields as field>
	/**
	 * 
	 * @return ${field.columnComment}
	 */
	public ${field.filedType} get${field.fieldName?cap_first}(){
		return ${field.fieldName};
	}
	
	/**
	 * 
	 * @param ${field.fieldName} ${field.columnComment}
	 */	
	public void set${field.fieldName?cap_first} (${field.filedType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	
	</#list>


}
