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
 * @since 2023-07-25
 */
@Getter
@Setter
@Table("t_user_classic")
public class UserClassic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long classicId;

    private Integer isFav;

    private Integer type;

}
