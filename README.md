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
