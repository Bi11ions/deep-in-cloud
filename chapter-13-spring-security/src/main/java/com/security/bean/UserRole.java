package com.security.bean;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 13:15
 */
@Data
@Table(name = "user_role")
public class UserRole {
    public static final String USER_ID = "userId";
    public static final String ROLE_ID = "roleId";

    private String id;
    @NotNull
    private String userId;
    @NotNull
    private String roleId;
}
