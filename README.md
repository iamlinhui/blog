# blog
- 一个适配移动端的,是一个能定制的web应用
- http://promptness.cn
- 当工作充斥满生活的时候  零碎的知识就更加需要整理


```text
# Dockerfile for blog
# 替换 http://apollo-host:port 配置中心的真实地址
# 1. mvn clean package -Dmaven.test.skip=true
# 2. Build with: docker build -f Dockerfile -t blog .
# 3. Run with: docker run -p 8080:8080 -d -v /tmp/logs/blog:/logs/blog --name blog blog
```

```text
# 我们一般可能会在容器启动后进入容器，常用的是docker attach 镜像id，但是启动镜像的时候如果没有带 参数 -it的话，
# attach进去后可能是日志界面，并不能执行命令。所以我们会用docker exec -it 镜像id /bin/bash/
# 平常的容器一般都可以执行/bin/bash，很是alpine没有，改成 docker exec -it 镜像id sh 就好了。
# CTRL + P + Q 容器不停止退出
```

```properties
#基础配置
spring.application.name = blog
server.apr = false
server.servlet.session.timeout = 1h
server.servlet.session.cookie.name = ___blog_______
#server.servlet.session.cookie.max-age = 1h
server.servlet.session.cookie.http-only = true
server.servlet.jsp.init-parameters.trimSpaces = true
spring.mvc.view.prefix = /WEB-INF/templates/
spring.mvc.view.suffix = .jsp

#数据库
spring.datasource.username = username
spring.datasource.password = password
spring.datasource.url = jdbc:mysql://mysql:3306/db_blog?useSSL=false&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
spring.datasource.type = com.zaxxer.hikari.HikariDataSource

#日志
logging.path = /logs/blog/
logging.level.root = error
logging.level.cn.promptness = error
logging.file = stdout.log

#mybatis和ehcache
mybatis.type-aliases-package = cn.promptness.blog.pojo
mybatis.mapper-locations = classpath:/mapper/*.xml
mybatis.configuration.map-underscore-to-camel-case = true
pagehelper.helperDialect = mysql
pagehelper.reasonable = true
pagehelper.supportMethodsArguments = true
pagehelper.params = count=countSql
spring.cache.ehcache.config = classpath:/ehcache.xml
spring.cache.type = ehcache

#mail
spring.mail.host = smtp.qq.com
spring.mail.port = 465
spring.mail.password = password
spring.mail.username = username
spring.mail.properties.mail.smtps.auth = true
spring.mail.properties.mail.smtps.starttls.enable = true
spring.mail.properties.mail.smtps.starttls.required = true
spring.mail.properties.mail.smtp.ssl.enable = true

#对象存储
qiniu.access-key = 
qiniu.secret-key =
qiniu.bucket-name = 
qiniu.image-host =
qiniu.local-bucket-name =
```
