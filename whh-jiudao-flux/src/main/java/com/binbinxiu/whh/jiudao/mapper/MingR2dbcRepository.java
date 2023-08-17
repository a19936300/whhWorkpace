package com.binbinxiu.whh.jiudao.mapper;

import com.binbinxiu.whh.jiudao.entity.Ming;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MingR2dbcRepository extends R2dbcRepository<Ming,Long> {
}
