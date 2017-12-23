spring boot demo

本地swagger访问地址：http://127.0.0.1:8081/demo/swagger-ui.html


添加spring-boot-starter-actuator依赖后
模块根据应用依赖和配置自动创建出来的监控和管理端点。通过这些端点，我们可以实时的获取应用的各项监控指标，
比如：访问/health端点，我们可以获得如下返回的应用健康信息：{"status":"UP"}

如果根据端点的作用来说，我们可以原生端点分为三大类：
1、应用配置类：获取应用程序中加载的应用配置、环境配置、自动化配置报告等与Spring Boot应用密切相关的配置类信息。
1）、/autoconfig：该端点用来获取应用的自动化配置报告，其中包括所有自动化配置的候选项。同时还列出了每个候选项自动化配置的各个先决条件是否满足。
所以，该端点可以帮助我们方便的找到一些自动化配置为什么没有生效的具体原因。该报告内容将自动化配置内容分为两部分：
    positiveMatches中返回的是条件匹配成功的自动化配置
    negativeMatches中返回的是条件匹配不成功的自动化配置
2）、/beans：该端点用来获取应用上下文中创建的所有Bean
每个bean中都包含了下面这几个信息：
    bean：Bean的名称
    scope：Bean的作用域
    type：Bean的Java类型
    reource：class文件的具体路径
    dependencies：依赖的Bean名称
3）、/configprops：该端点用来获取应用中配置的属性信息报告
从下面该端点返回示例的片段中，我们看到返回了关于该短信的配置信息，prefix属性代表了属性的配置前缀，properties代表了各个属性的名称和值。
所以，我们可以通过该报告来看到各个属性的配置路径，比如我们要关闭该端点，就可以通过使用endpoints.configprops.enabled=false来完成设置.
{
    "configurationPropertiesReportEndpoint": {
        "prefix": "endpoints.configprops",
        "properties": {
            "id": "configprops",
            "sensitive": true,
            "enabled": true
        }
    },
    ...
}
4）、/env：该端点与/configprops不同，它用来获取应用所有可用的环境属性报告，包括：环境变量、JVM属性、应用的配置配置、命令行中的参数。
从下面该端点返回的示例片段中，我们可以看到它不仅返回了应用的配置属性，还返回了系统属性、环境变量等丰富的配置信息，
其中也包括了应用还没有没有使用的配置。所以它可以帮助我们方便地看到当前应用可以加载的配置信息，并配合@ConfigurationProperties注解将它们引入到我们的应用程序中来进行使用。
另外，为了配置属性的安全，对于一些类似密码等敏感信息，该端点都会进行隐私保护，但是我们需要让属性名中包含：password、secret、key这些关键词，
这样该端点在返回它们的时候会使用*来替代实际的属性值。
5）、/mappings：该端点用来返回所有Spring MVC的控制器映射关系报告。
该报告的信息与我们在启用Spring MVC的Web应用时输出的日志信息类似，其中bean属性标识了该映射关系的请求处理器，method属性标识了该映射关系的具体处理类和处理函数。
{
    "/webjars/**":{
        "bean":"resourceHandlerMapping"
    },
    "{[/hello],methods=[GET]}":{
        "bean":"requestMappingHandlerMapping",
        "method":"public java.lang.String top.xudj.demo.controller.HelloController.index()"
    }
}
6）、/info：该端点用来返回一些应用自定义的信息。默认情况下该端点只会返回一个空的json内容。
我们可以在application.properties配置文件中通过info前缀来设置一些属性，比如下面这样：
    info.app.name=spring-boot-hello
    info.app.version=v1.0.0
再访问/info端点，我们可以得到下面的返回报告，其中就包含了上面我们在应用自定义的两个参数
{
    "app": {
        "name": "spring-boot-hello",
        "version": "v1.0.0"
    }
}


2、度量指标类：获取应用程序运行过程中用于控制的度量指标，比如：内存信息、线程池、HTTP请求统计等。
上面我们所介绍的应用配置类端点所提供的信息报告在应用启动的时候都已经基本确定了其返回内容，可以说是一个静态报告。
而度量指标类端点提供的报告内容则是动态变化的，这些端点提供了应用程序在运行过程中的一些快照信息，
比如：内存使用情况、HTTP请求统计、外部资源指标等。这些端点对于我们构建微服务架构中的监控系统非常有帮助，
由于Spring Boot应用自身实现了这些端点，所以我们可以很方便地利用它们来收集我们想要的信息，以制定出各种自动化策略。
1）、/metrics：该端点用来返回当前应用的各类重要度量指标，比如：内存信息、线程信息、垃圾回收信息等。
系统信息：包括处理器数量processors、运行时间uptime和instance.uptime、系统平均负载systemload.average。
mem.*：内存概要信息，包括分配给应用的总内存数量以及当前空闲的内存数量。这些信息来自java.lang.Runtime。
heap.*：堆内存使用情况。这些信息来自java.lang.management.MemoryMXBean接口中getHeapMemoryUsage方法获取的java.lang.management.MemoryUsage。
nonheap.*：非堆内存使用情况。这些信息来自java.lang.management.MemoryMXBean接口中getNonHeapMemoryUsage方法获取的java.lang.management.MemoryUsage。
threads.*：线程使用情况，包括线程数、守护线程数（daemon）、线程峰值（peak）等，这些数据均来自java.lang.management.ThreadMXBean。
classes.*：应用加载和卸载的类统计。这些数据均来自java.lang.management.ClassLoadingMXBean。
gc.*：垃圾收集器的详细信息，包括垃圾回收次数gc.ps_scavenge.count、垃圾回收消耗时间gc.ps_scavenge.time、标记-清除算法的次数gc.ps_marksweep.count、标记-清除算法的消耗时间gc.ps_marksweep.time。这些数据均来自java.lang.management.GarbageCollectorMXBean。
httpsessions.*：Tomcat容器的会话使用情况。包括最大会话数httpsessions.max和活跃会话数httpsessions.active。该度量指标信息仅在引入了嵌入式Tomcat作为应用容器的时候才会提供。
gauge.*：HTTP请求的性能指标之一，它主要用来反映一个绝对数值。比如上面示例中的gauge.response.hello: 5，它表示上一次hello请求的延迟时间为5毫秒。
counter.*：HTTP请求的性能指标之一，它主要作为计数器来使用，记录了增加量和减少量。如上示例中counter.status.200.hello: 11，它代表了hello请求返回200状态的次数为11。
对于gauge.*和counter.*的统计，这里有一个特殊的内容请求star-star，它代表了对静态资源的访问。
这两类度量指标非常有用，我们不仅可以使用它默认的统计指标，还可以在程序中轻松的增加自定义统计值。
只需要通过注入org.springframework.boot.actuate.metrics.CounterService和org.springframework.boot.actuate.metrics.GaugeService来实现自定义的统计指标信息。
比如：我们可以像下面这样自定义实现对hello接口的访问次数统计。
@RestController
public class HelloController {
    @Autowired
    private CounterService counterService;
    @RequestMapping("/hello")
    public String greet() {
        counterService.increment("didispace.hello.count");
        return "";
    }
}
另外还可以通过/metrics/{name}接口来更细粒度的获取度量信息。
2）、/health：用来获取应用的各类健康指标信息。在spring-boot-starter-actuator模块中自带实现了一些常用资源的健康指标检测器。这些检测器都通过HealthIndicator接口实现，
并且会根据依赖关系的引入实现自动化装配，比如用于检测磁盘的DiskSpaceHealthIndicator、检测DataSource连接是否可用的DataSourceHealthIndicator等
{
    "status":"UP",
    "diskSpace":{
        "status":"UP",
        "total":249779191808,
        "free":73223319552,
        "threshold":10485760
    },
    "db":{
        "status":"UP",
        "database":"MySQL",
        "hello":1
    }
}
我们可能还会用到一些Spring Boot的Starter POMs中还没有封装的产品来进行开发，比如：当使用RocketMQ作为消息代理时，由于没有自动化配置的检测器，所以我们需要自己来实现一个用来采集健康信息的检测器。
比如，我们可以在Spring Boot的应用中，为org.springframework.boot.actuate.health.HealthIndicator接口实现一个对RocketMQ的检测器类。
3）、/dump：该端点用来暴露程序运行中的线程信息。它使用java.lang.management.ThreadMXBean的dumpAllThreads方法来返回所有含有同步信息的活动线程详情。
4）、/trace：该端点用来返回基本的HTTP跟踪信息。默认情况下，跟踪信息的存储采用org.springframework.boot.actuate.trace.InMemoryTraceRepository实现的内存方式，
始终保留最近的100条请求记录。


3、操作控制类：提供了对应用的关闭等操作类功能。
由于之前介绍的所有端点都是用来反映应用自身的属性或是运行中的状态，相对于操作控制类端点没有那么敏感，所以他们默认都是启用的。
而操作控制类端点拥有更强大的控制能力，如果要使用它们的话，需要通过属性来配置开启。
在原生端点中，只提供了一个用来关闭应用的端点：/shutdown。我们可以通过如下配置开启它
endpoints.shutdown.enabled=true
