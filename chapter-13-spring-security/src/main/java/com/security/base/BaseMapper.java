package com.security.base;


import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 13:50
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>,
        InsertListMapper<T> {
}
