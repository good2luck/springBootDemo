spring boot demo

我们引入了spring-boot-starter，其中包含了spring-boot-starter-logging，该依赖内容就是Spring Boot默认的日志框架Logback，
所以我们在引入log4j之前，需要先排除该包的依赖，再引入log4j的依赖

分类输出
    按包输出
top.xudj.demo.service包下的日志配置
log4j.category.top.xudj.demo.service=DEBUG, servicefile
# top.xudj.demo.service下的日志输出配置
...
    按不同级别进行分类
log4j.logger.error=errorfile
# error日志输出配置
...


另：如若想支持多环境下的不同日志级别，可在log4j.properties中通过配置文件中的参数引用功能进行配置，则启动不同的环境的yml文件，
会传入不同的日志级别配置


将日志输出到mongodb中：
思路：使用log4mongo搭建