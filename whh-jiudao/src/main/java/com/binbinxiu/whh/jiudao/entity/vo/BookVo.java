package com.binbinxiu.whh.jiudao.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.binbinxiu.whh.jiudao.config.BaseEntity;
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
public class BookVo {

    private String author;

    private Integer favNums;

    private String image;

    private String title;

    private Long id;
}
