package com.snackpub.core.redis.aop.db.mapper;


import com.snackpub.core.redis.aop.db.model.WebLog;


public interface WebLogMapper {


    int insert(WebLog record);

}