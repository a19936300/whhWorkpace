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
 * @since 2023-08-04
 */
@Getter
@Setter
@TableName("t_book")
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String author;

    private Integer favNums;

    private String image;

    private String title;

    private Long oldId;
}
