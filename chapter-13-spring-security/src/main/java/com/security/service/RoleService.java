package com.security.service;

import com.security.bean.Role;
import com.security.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 14:13
 */
@Service
public class RoleService {
    @Resource
    private RoleMapper mapper;

    public Role get(String id) {
        Assert.hasText(id, "id can not be null");

        return mapper.selectByPrimaryKey(id);
    }

    public Integer insert(@Valid Role role) {
        return mapper.insert(role);
    }

    public Integer update(@Valid Role role) {
        return mapper.updateByPrimaryKeySelective(role);
    }

    public void delete(String id) {
        Assert.hasText(id, "id can not be null");

        mapper.deleteByPrimaryKey(id);
    }

    public List<Role> listByCondition(Role role) {
        if (null == role) {
            return new ArrayList<>(0);
        }

        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(role.getName())) {
            criteria.andEqualTo(Role.NAME, role.getName());
        }

        if (null != role.getId()) {
            criteria.andEqualTo(Role.ID, role.getId());
        } else if (!CollectionUtils.isEmpty(role.getIdList())) {
            criteria.andIn(Role.ID, role.getIdList());
        }

        return mapper.selectByExample(example);
    }
}
