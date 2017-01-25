package com.hawk.codegen.database.parse;

import com.hawk.codegen.database.meta.Database;

public interface IDatabaseParser {
	Database parse() throws  Throwable;
}
