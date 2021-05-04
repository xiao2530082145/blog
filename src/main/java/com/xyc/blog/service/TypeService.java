package com.xyc.blog.service;

import com.xyc.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);


    Type getTypeById(Long id);


    Type getTypeByName(String name);


    List<Type> getAllTypes();

    List<Type> getDetailTypes();


    int updateType(Type type);


    int deleteType(Long id);
}
