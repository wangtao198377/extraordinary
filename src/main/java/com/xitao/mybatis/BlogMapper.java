package com.xitao.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

    @Select("select count(*) from test1")
    public Integer selectBlog();
}
