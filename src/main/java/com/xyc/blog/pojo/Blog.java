package com.xyc.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Long id;//id
    private String title;//标题
    private String content;//内容
    private String blogDescription;//博客描述
    private String firstPicture;//图片地址
    private String flag;//原创 or 转载
    private Integer views;//阅读数
    private boolean appreciation;//赞赏开关
    private boolean shareStatement;//版权开关
    //    private boolean published;//草稿还是发布状态
//    private boolean recommend;//推荐开关
    private Date createTime;//创作时间
    private Date updateTime;//最后修改时间

    //用于数据库连接查询
    private Long typeId;
    private Long userId;

    private Type type;  //多对一
    private User user;  //多对一


}
