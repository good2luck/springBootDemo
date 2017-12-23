spring boot demo

本地swagger访问地址：http://127.0.0.1:8081/demo/swagger-ui.html


Message Broker与AMQP简介
Message Broker是一种消息验证、传输、路由的框架模式，其设计目标主要应用与下面这些场景：
    消息路由到一个或多个目的地
    消息转化成其它的表现方式
    执行消息的聚集、消息的分解，并将结果发送到他们的目的地，然后重新组合相应返回给消息用户
    调用Web服务来检索数据
    响应事件或错误
    使用发布-订阅模式来提供内容或基于主题的消息路由
AMQP是Advanced Message Queuing Protocol的简称，它是一个面向消息中间件的开放式标准应用层协议。AMQP定义了如下属性：
    消息方向
    消息队列
    消息路由（包括：点到点和发布-订阅模式）
    可靠性
    安全性

RabbitMQ
RabbitMQ便是以AMQP协议实现的一种中间件产品，可支持多种操作系统，多种编程语言，几乎可以覆盖所有主流的企业级技术平台。

    