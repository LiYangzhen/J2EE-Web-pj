# 项目文档

## 项目目标
学习使用J2EE相关技术，以MVC架构为基础，完成课程项目

该项目为搭建一个旅游图片分享平台网站。

## 代码结构说明
主要使用了MVC模式（个别页面仅使用jsp），底层采取了DAO+JavaBeen的实现方法

```seq
jsp->servlet:请求页面
servlet->DAO:请求数据
DAO->servlet:发送数据库信息
servlet->jsp:返回页面所需信息
```

## 项目结构
```
/out
/src
    /class
    /dao
    /db
    /domain
    /impl
    /javabeen
    /servlet
    ...
/web
    /css
    /image
    /js
    /travel-images
    /WEB-INF
    /index.jsp
    /...
/README.md
...
```