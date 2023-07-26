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
@TableName("t_classic")
public class Classic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer favNums;

    private String content;

    private String image;

    private String classicIndex;

    private Double pubdate;

    private String title;

    private String type;
}
