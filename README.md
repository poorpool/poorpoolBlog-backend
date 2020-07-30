# poorpoolBlog-backend
个人博客系统——基于Spring Boot打造。

大约还用到了 Mybatis 及逆向工程，jwt。

因涉及敏感信息，application.yml 没有提交。路径为`src/main/resources/application.yml`

```yaml
server:
  port: 8181

spring:
  datasource:
    name: test
    url: # 数据库 url
    username: # 数据库用户名
    password: # 数据库密码
    # 使用druid数据源
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    dbcp2:
      initial-size: 1
      max-wait-millis: 60000
      min-idle: 1
      time-between-eviction-runs-millis: 60000
      validation-query: select 'x'
      test-on-borrow: false
      test-on-return: false
      test-while-idle: true
      max-total: 20

mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml
##  type-aliases-package: net.yxchen.poorpoolblog.bean
```
