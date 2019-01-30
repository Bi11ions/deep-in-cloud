package com.security.service;

import com.security.bean.Blog;

import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 10:09
 */
public interface IBlogService {

    List<Blog> getBlogs();

    void deleteBlog(long id);
}
