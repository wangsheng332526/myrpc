看了一段时间dubbox的源码后,写了几个功能完成自己的rpc框架. 

整合了spring,用netty通讯,zookeeper为注册中心.

服务提供者用netty发布服务并将服务地址注册到zookeeper,等待调用.

服务调用者在注册中心找到服务提供者的地址,使用动态代理,传递调用方法和参数去调用netty服务端,然后服务提供者使用反射执行方法后将结果返回给调用者

先运行TimeServer.java,再运行Test.java查看执行结果
