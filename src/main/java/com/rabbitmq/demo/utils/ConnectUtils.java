package com.rabbitmq.demo.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.demo.ssl.NoCheckTrustManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectUtil
 * @Description 获取连接的工具类
 * @Author shanghao5
 * @Date 2018/11/27 19:59
 **/
public class ConnectUtils {

    /**
     * TLS version which should be used.
     */
    private static final String TLS_TYPE = "TLSv1.2";

    /**
     * Rabbitmq port to listen.
     */
    private static final int RABBIT_MQ_PORT = 7102;
    private static final boolean SSL_FLAG = true;
    private static final String RABBIT_MQ_USERNAME = "root";
    private static final String RABBIT_MQ_PASSWORD = "fzO1C3pJ";


    public  static final String HOST = "10.19.131.13";
//    public  static final String HOST = "10.19.131.69";

    public static Connection getConnect() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {


        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(HOST);
        //端口
        //amqp协议 端口 类似与mysql的3306
        factory.setPort(RABBIT_MQ_PORT);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername(RABBIT_MQ_USERNAME);
        factory.setPassword(RABBIT_MQ_PASSWORD);
        // 是否支持ssl
        if(SSL_FLAG){
            SSLContext sslContext = SSLContext.getInstance(TLS_TYPE);
            // 跳过验证
            TrustManager[] dummyTrustManager = new TrustManager[] { new NoCheckTrustManager() };
            sslContext.init(null, dummyTrustManager, null);

            factory.useSslProtocol(sslContext);
        }
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
