# ServerWindows_Server
ServerWindows监控平台的服务器端

## 功能
负责响应客户端发来的请求，将指定主机的监控数据发给客户端。

## 开发及运行平台
Java： jdk1.8.0_181及以下（java10可能会由于时间戳转换格式的区别，导致与服务器交互的json格式出现错误）。
WEB容器：推荐Tomcat8、9
数据库：InfluxDB 1.5.4

## 使用技术
本项目涉及到如下技术：

Gson：Google推出的**Json序列化/反序列化的类库**。本项目使用该记录来实现服务器端与客户端的交互。

Maven：依赖包管理器，所有的依赖包都将会在本项目代码根目录下的**pom.xml**中列出。

influxdb-java：This is the Java Client library.将查询到的数据注入到List<Point类>中。

## 项目架构
ServerWindows是一个监控平台，由三个模块组成，本项目为其中的服务器端。  
*本文为服务器端的说明书，所以平台架构不予赘述，平台架构在个人博客中说明：*[https://imdyc.com/java/serverwindows](https://imdyc.com/java/serverwindows-%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%8F%AF%E8%A7%86%E5%8C%96%E7%9B%91%E6%8E%A7%E5%B9%B3%E5%8F%B0.html)

下图是本项目的架构
![项目架构](http://images-1252121815.cosgz.myqcloud.com/blog/ServerWindows/TIM%E6%88%AA%E5%9B%BE20180812084750.png)
本项目未使用任何框架，采用influxdb-java（的官方驱动），将数据获取后，经Service中的逻辑处理，最后通过Servlet将数据发送给Andeoid端。



## 其他
本项目以Post方式响应Android的请求，同时也预留了doGet接口，直接在浏览器访问便可以获取对应路径的Json字符串，方便开发过程中的调试和使用时的问题排查。
