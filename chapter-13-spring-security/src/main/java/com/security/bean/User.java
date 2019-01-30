package com.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 11:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {
    public static final String USER_NAME = "username";

    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @Transient
    private List<Role> authorities;
}
