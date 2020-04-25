package com.abc.dao;

import com.abc.entity.Yyuser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface YyuserMapper {
    int insert(Yyuser record);

    List<Yyuser> selectAll();

    Yyuser selectById(Long id);
}