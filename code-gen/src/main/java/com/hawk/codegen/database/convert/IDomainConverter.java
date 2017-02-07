package com.hawk.codegen.database.convert;

import com.hawk.codegen.database.meta.Domain;
import com.hawk.codegen.database.meta.Table;

public interface IDomainConverter {
	
	public Domain convert(Table table);

}
