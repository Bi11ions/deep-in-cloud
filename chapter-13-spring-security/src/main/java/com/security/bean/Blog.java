package com.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 10:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog {
    private Long id;
    private String name;
    private String content;
}
