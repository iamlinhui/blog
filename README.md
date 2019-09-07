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