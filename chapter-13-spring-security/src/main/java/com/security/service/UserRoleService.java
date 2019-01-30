package com.security.service;

import com.security.bean.UserRole;
import com.security.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 14:17
 */
@Service
public class UserRoleService {
    @Resource
    private UserRoleMapper mapper;

    public UserRole get(String id) {
        Assert.hasText(id, "id can not be null");

        return mapper.selectByPrimaryKey(id);
    }

    public Integer insert(@Valid UserRole userRole) {
        return mapper.insert(userRole);
    }

    public Integer update(@Valid UserRole userRole) {
        return mapper.updateByPrimaryKeySelective(userRole);
    }

    public void delete(String id) {
        Assert.hasText(id, "id can not be null");

        mapper.deleteByPrimaryKey(id);
    }

    public List<UserRole> listByCondition(UserRole userRole) {
        Example example = new Example(UserRole.class);
        if (null == userRole) {
            return new ArrayList<>(0);
        }

        Example.Criteria criteria = example.createCriteria();
        if (null != userRole.getRoleId()) {
            criteria.andEqualTo(UserRole.ROLE_ID, userRole.getRoleId());
        }

        if (null != userRole.getUserId()) {
            criteria.andEqualTo(UserRole.USER_ID, userRole.getUserId());
        }

        return mapper.selectByExample(example);
    }
}
