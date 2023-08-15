package com.binbinxiu.whh.jiudao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.binbinxiu.whh.jiudao.config.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class Classic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    protected Long oldId;

    private Integer favNums;

    private String content;

    private String image;

    private Integer classicIndex;

    private String pubdate;

    private String title;

    private Integer type;
}
