package com.hawk.framework.dic.persist.mapperex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hawk.framework.dic.persist.domainex.SynonymExDomain;

public interface SynonymExMapper {
	
	public List<SynonymExDomain> querySynonymEx(@Param("synonymType")String synonymType, @Param("systemCode")String systemCode , @Param("version")int version);

}
