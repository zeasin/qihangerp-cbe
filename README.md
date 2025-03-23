# 启航电商ERP系统-跨境版

## 一、项目概述

启航电商ERP系统-跨境版是一套为跨境电商卖家开发的ERP系统，主体功能包括：商品库、同步商品到电商平台、店铺商品管理、店铺分类属性管理、店铺库存管理、订单库、店铺订单管理、库存管理等功能。

目前支持shopfiy独立站和Shein，后续计划支持Amazon、Temu、TikTokShop等。


本系统后端采用SpringCloud微服务架构，前端采用Vue2+Element。



## 二、项目说明
**本系统后端采用SpringCloud微服务架构，前端采用vue+element**

### 2.1 主要版本
+ 后端：
  + `Java`: 17
  + `SpringBoot`: 3.0.13
  + `SpringCloud`：2022.0.0
+ 前端：
  + `vue2`
  + `elementUI`

### 2.2 存储及中间件
+ `MySQL`:数据库,版本8.x。
+ `minio文`:文件存储,用于图片存储。
+ `Redis`:在线用户信息、缓存。
+ `Nacos`:版本2.3.2以上，注册中心。

### 2.3 项目结构
#### 2.3.1 gateway
**网关Gateway，使用8088端口**

#### 2.3.2 oms-api
**系统主体OMS功能模块，对接各大平台接口**

#### 2.3.3 sys-api
**系统模块**


#### 2.3.4 common
公共类库

#### 2.3.6 vue
**前端项目**


## 三、如何使用？
### 3.1、开发环境配置
+ MySQL数据库创建
  + 运行MySQL脚本`docs\sql\qihangerp-cbe.sql`导入数据到主库`qihangerp-cbe`

+ 启动nacos
  + 启动
  
    **一般情况下：下载nacos2.3.2解压之后直接运行：**
  
    `startup.cmd -m standalone`

+ 启动Redis

  
+ 启动minio


  
### 3.2、启动后端

+ 启动`api`下面的所有微服务
+ 启动`oms-api`项目
+ 启动`sys-api`项目

### 3.3、启动前端 `vue`
+ `npm install`
+ `npm run dev`
+ 打包`npm run build:prod`
+ 访问web
  + 访问地址：`http://localhost`
  + 登录名：`admin`
  + 登录密码：`admin123`




## 四、支持一下

**感谢大家的关注与支持！希望利用本人从事电商10余年的经验帮助到大家提升工作效率！**

### 4.1 赠人玫瑰手留余香
💖 如果觉得有用记得点个 Star⭐

<img src="docs/star.jpg" width="300px" />

### 4.2 一起交流

💖 欢迎加入知识星期获得作者帮助和朋友们一起交流！

+ 知识星球

   <img src="docs/知识星球.jpg" width="300px" />


### 4.3 捐助作者
作者为兼职做开源,平时还需要工作,如果帮到了您可以请作者吃个盒饭

<img src="docs/微信收款码.jpg" width="300px" />
<img src="docs/支付宝收款码.jpg" width="300px" />

### 4.4 获取授权与服务

[如何获取授权与服务](https://mp.weixin.qq.com/s/mATn2nfc5bUePohS_s2WGw)

