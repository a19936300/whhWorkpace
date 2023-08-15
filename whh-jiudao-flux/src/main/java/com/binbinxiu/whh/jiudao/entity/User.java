package com.binbinxiu.whh.jiudao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.binbinxiu.whh.jiudao.config.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Getter
@Setter
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String passWord;

    private String nickName;

}
