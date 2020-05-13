2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/builder/HttpClientManager.java
package com.chad.wechatdev.commons.builder;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;



public class HttpClientManager {

        private static PoolingHttpClientConnectionManager pcm = null;
        private static HttpClientManager hcm = null;

        private HttpClientManager(){

        }

        public static HttpClientManager getInstance(){
            synchronized(HttpClientManager.class){
                if(pcm == null){
                /*try {
                    Thread.sleep(100);//将问题极端化
                } catch (InterruptedException e) {
                }*/
                    LayeredConnectionSocketFactory sslsf = null;
                    try {
                        sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }


                    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                            .register("https", sslsf)
                            .register("http", new PlainConnectionSocketFactory())
                            .build();
                    pcm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                    pcm.setMaxTotal(10);
                    pcm.setDefaultMaxPerRoute(5);
                    hcm = new HttpClientManager();
                    };

                }
          return hcm;
        }
        /***
         * 注释的重连操作
         * @return
         */
       public CloseableHttpClient getHttpClient() {
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(pcm)
                    .build();

            return httpClient;
        }




}
