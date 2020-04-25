package com.abc.servive;

import com.abc.entity.Yyuser;

import java.util.List;

public interface YYUserService {

    int insert(Yyuser record);

    List<Yyuser> selectAll();

    Yyuser selectById(Long id);
}
