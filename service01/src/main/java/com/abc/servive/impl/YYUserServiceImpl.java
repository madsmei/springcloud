package com.abc.servive.impl;

import com.abc.dao.YyuserMapper;
import com.abc.entity.Yyuser;
import com.abc.servive.YYUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class YYUserServiceImpl implements YYUserService {

    @Autowired
    private YyuserMapper mapper;

    @Override
    public int insert(Yyuser record) {
        return mapper.insert(record);
    }

    @Override
    public List<Yyuser> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Yyuser selectById(Long id) {
        return mapper.selectById(id);
    }
}
