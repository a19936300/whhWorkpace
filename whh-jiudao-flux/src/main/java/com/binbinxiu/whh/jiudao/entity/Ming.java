package com.binbinxiu.whh.jiudao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user")
@NoArgsConstructor
@AllArgsConstructor
public class Ming implements Persistable<Integer> {
    @Id
    private Integer id;
    private String ming;

    @Override
    public boolean isNew() {
        return true;
    }
}
