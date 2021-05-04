package com.xyc.blog.service.impl;


import com.xyc.blog.dao.TypeDao;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.pojo.Type;
import com.xyc.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getAllTypes() {
        return typeDao.getAllTypes();
    }

    @Override
    public List<Type> getDetailTypes() {
        return typeDao.getDetailTypes();
    }


    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
