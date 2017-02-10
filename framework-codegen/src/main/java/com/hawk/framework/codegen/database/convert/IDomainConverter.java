package com.hawk.framework.codegen.database.convert;

import com.hawk.framework.codegen.database.meta.Domain;
import com.hawk.framework.codegen.database.meta.Table;

public interface IDomainConverter {
	
	public Domain convert(Table table);

}
