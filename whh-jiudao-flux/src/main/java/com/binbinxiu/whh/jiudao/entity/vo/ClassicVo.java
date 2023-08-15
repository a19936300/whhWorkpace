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
 * @since 2023-07-25
 */

@Getter
@Setter
public class ClassicVo {
    private Long id;

    private Integer favNums;

    private String content;

    private String image;

    private Integer index;

    private Integer likeStatus;

    private String pubdate;

    private String title;

    private Integer type;
}
