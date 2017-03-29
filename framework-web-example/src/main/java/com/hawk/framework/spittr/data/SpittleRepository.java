package com.hawk.framework.spittr.data;

import java.util.List;

import com.hawk.framework.spittr.Spittle;



public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  void save(Spittle spittle);

}
