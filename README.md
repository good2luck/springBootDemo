spring boot demo

在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识，比如：
application-dev.properties：开发环境
application-test.properties：测试环境
application-prod.properties：生产环境
至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值,
且指定环境的配置会覆盖application.properties的相同配置