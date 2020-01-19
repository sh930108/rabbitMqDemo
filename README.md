# rabbitMqDemo
rabbitMq各种模式demo

# putty 使用:
1. 安装putty
2. 使用注册表修改putty样式
3. 使用notepad++ 添加快捷键：运行 ---> 运行 C:\Windows\SysWOW64\cmd.exe /k "$(CURRENT_WORD)" & EXIT 或者 cmd /k "$(CURRENT_WORD)" & EXIT 保存快捷键
4. 重启notepad++

# 参考命令
"C:\Program Files\PuTTY\putty.exe" -load igvita-desert -pw Hik@123++ root@10.19.131.13 
"C:\Program Files (x86)\WinSCP\winscp.exe" sftp://root:Hik%40123%2B%2B@10.19.131.13:22/home 

# notepad++ 宏
https://blog.csdn.net/a40850273/article/details/80761884


#kafka常用指令
 
 bin/zookeeper-server-start.sh config/zookeeper.properties
 JMX_PORT=7899 bin/kafka-server-start.sh config/server.properties
 
 ./kafka-consumer-groups.sh --bootstrap-server localhost:9093 --list
 ./kafka-consumer-groups.sh --bootstrap-server localhost:9093 --describe --group group-2
 ./kafka-console-producer.sh --bootstrap-server localhost:9093 --describe --group group-2
 
 kafka 监控 KafkaOffsetMonitor
 /opt/jre18linux64.1/bin/java -cp KafkaOffsetMonitor-assembly-0.2.0.jar com.quantifind.kafka.offsetapp.OffsetGetterWeb --zk 10.19.128.118:2181 --port 9099  --refresh 5.seconds --retain 1.days
 jar打开，offsetapp 下index.html 替换
 
     <script src="//cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
 	<script src="//cdn.static.runoob.com/libs/angular.js/1.4.6/angular-route.js"></script>
 	<script src="//cdn.static.runoob.com/libs/angular.js/1.4.6/angular-resource.js"></script>
 	
#
curl -u admin:vfD25S7A -d "body=message" http://10.19.131.13:8288/api/message/ShanghaoTEST?type=queue

#抓包
 tcpdump -i lo -nn tcp port 7002|grep "> 10.2.145.79.7002"|awk -F " " '{print $3}'|awk -F "." '{cmd="lsof -i :"$5;system(cmd)}'
 
 
 # jcmd
 /opt/hikvision/web/components/openjdk8linux64arm.1/bin/jcmd  47424 Thread.print 
 /opt/hikvision/web/components/openjdk8linux64arm.1/bin/jcmd  47424 GC.heap_dump /opt/tomcat.hprof
 
 git的时候出现问题SSL Error: unable to get local issuer certificate
 跳过ssh验证
 git config --global http.sslVerify false
 
 ## ps将某个进程显示出来 显示java的进程
    ps -ef |grep java			//显示所有命令，连带命令行
    ps -aux |grep java       	//-aux 显示所有包含其他使用者的行程 	