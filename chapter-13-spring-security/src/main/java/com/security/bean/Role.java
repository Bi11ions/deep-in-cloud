package com.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 11:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "role")
public class Role {
    public static final String ID = "id";
    public static final String NAME = "name";

    private String id;
    @NotNull
    private String name;

    @Transient
    private List<String> idList;
}
