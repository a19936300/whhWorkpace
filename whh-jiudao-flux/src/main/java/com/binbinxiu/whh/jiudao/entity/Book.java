package com.binbinxiu.whh.jiudao.entity;

import com.binbinxiu.whh.jiudao.config.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("t_book")
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String author;

    private Integer favNums;

    private String image;

    private String title;

    private Long oldId;
}
