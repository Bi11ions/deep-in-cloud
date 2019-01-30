package com.security.controller;

import com.security.bean.Blog;
import com.security.service.BlogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 10:16
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("")
    public ModelAndView list(Model model) {
        List<Blog> blogs = blogService.getBlogs();
        model.addAttribute("blogsList", blogs);
        return new ModelAndView("blogs/list", "blogModel", model);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ModelAndView delete(@PathVariable Long id, Model model) {
        blogService.deleteBlog(id);
        model.addAttribute("blogsList", blogService.getBlogs());
        return new ModelAndView("blogs/list", "blogModel", model);
    }
}
