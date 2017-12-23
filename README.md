spring boot demo

本地swagger访问地址：http://127.0.0.1:8081/demo/swagger-ui.html


banner.txt文件配置说明：
${AnsiColor.BRIGHT_RED}：设置控制台中输出内容的颜色
${application.version}：用来获取MANIFEST.MF文件中的版本号
${application.formatted-version}：格式化后的${application.version}版本信息,即上面的的版本号前面加v后上括号
${spring-boot.version}：Spring Boot的版本号
${spring-boot.formatted-version}：格式化后的${spring-boot.version}版本信息


可以借助下面这些工具，轻松地根据文字或图片来生成用于Banner输出的字符画。
http://patorjk.com/software/taag
http://www.network-science.de/ascii/
http://www.degraeve.com/img2txt.php
然后将ASCII字符画复制进banner.txt中，就能替换默认的banner了。