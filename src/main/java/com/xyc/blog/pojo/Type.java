package com.xyc.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Long id;
    private String name;
    private Integer blogNum;
    private List<Blog> blogs = new ArrayList<>();//一对多

}
