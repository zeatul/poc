package com.hawk.framework.spittr.data;

import com.hawk.framework.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
