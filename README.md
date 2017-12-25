spring boot demo

本地swagger访问地址：http://127.0.0.1:8081/demo/swagger-ui.html


spring boot的Actuator模块下/info端点：
该端点除了描述应用信息之外，也还可以用来描述Git版本信息，并且整合方法非常简单，
本例便是如何使用/info端点暴露当前应用的Git版本信息。
--> 添加git-commit-id-plugin插件并运行git-commit-id:reversion，该插件用来产生git的版本信息

默认只能看到branch与commit信息，可management.info.git.mode=full查看所有详细信息