package com.security.service;

import com.security.bean.Role;
import com.security.bean.User;
import com.security.bean.UserRole;
import com.security.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 13:40
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserMapper mapper;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;


    public User get(String id) {
        Assert.hasText(id, "id can not be null");

        return mapper.selectByPrimaryKey(id);
    }

    public User getByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo(User.USER_NAME, StringUtils.isEmpty(username) ? "" : username);
        User user = mapper.selectOneByExample(example);
        if (null == user) {
            return null;
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoleList = userRoleService.listByCondition(userRole);
        if (CollectionUtils.isEmpty(userRoleList)) {
            return user;
        }

        List<String> roleIdList = userRoleList.stream()
                .map(UserRole::getRoleId)
                .distinct()
                .collect(Collectors.toList());
        Role role = new Role();
        role.setIdList(roleIdList);
        List<Role> roleList = roleService.listByCondition(role);
        if (!CollectionUtils.isEmpty(roleIdList)) {
            user.setAuthorities(roleList);
        }

        return user;
    }

    public Integer insert(@Valid User user) {
        User existUser = getByName(user.getUsername());
        if (null != existUser) {
            return 0;
        }

        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return mapper.insert(user);
    }

    public Integer update(@Valid User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        Assert.hasText(id, "id can not be null");

        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByName(username);
        if (null == user) {
            return new org.springframework.security.core.userdetails.User("", "", new ArrayList<>(0));
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
