package com.binbinxiu.whh.jiudao.entity;

import com.binbinxiu.whh.jiudao.config.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table("t_classic")
public class Classic  {
    protected Long oldId;

    private Integer favNums;

    private String content;

    private String image;

    private Integer index;

    private Date pubdate;

    private String title;

    private Integer type;


    @Id
    protected Long id;

    private Integer revision;

    protected Date createdTime;

    protected Date updatedTime;

    protected Long createdBy;

    protected Long updatedBy;
}
