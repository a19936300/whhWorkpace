package com.binbinxiu.whh.jiudao.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table("t_user")
public class User{

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private @NonNull String name;

    private @NonNull String password;

    private @NonNull String nickname;

}
