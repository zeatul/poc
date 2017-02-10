package com.hawk.framework.codegen.database.parse;

import com.hawk.framework.codegen.database.meta.Database;

public interface IDatabaseParser {
	Database parse() throws  Throwable;
}
