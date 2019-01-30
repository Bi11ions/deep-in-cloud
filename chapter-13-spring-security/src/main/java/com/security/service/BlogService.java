package com.security.service;

import com.security.bean.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 10:10
 */
@Service
public class BlogService implements IBlogService {

    private List<Blog> list = new ArrayList<>();

    public BlogService() {
        list.add(new Blog(1L, "Spring in action", "good!"));
        list.add(new Blog(2L, "Spring boot in action", "nice!"));
    }

    @Override
    public List<Blog> getBlogs() {
        return list;
    }

    @Override
    public void deleteBlog(long id) {
        list.removeIf(blog -> id == blog.getId());
    }
}
