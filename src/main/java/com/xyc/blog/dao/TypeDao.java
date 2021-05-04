package com.xyc.blog.dao;

import com.xyc.blog.pojo.Blog;
import com.xyc.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);


    Type getTypeById(Long id);


    Type getTypeByName(String name);


    List<Type> getAllTypes();

    List<Type> getDetailTypes();

    int updateType(Type type);


    int deleteType(Long id);
}
