6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/NettyAutoConfiguration.java
package com.github.taoroot.taoiot.netty;

import com.github.taoroot.taoiot.netty.mqtt.MqttHandler;
import com.github.taoroot.taoiot.netty.mqtt.MqttHandlerProcessor;
import com.github.taoroot.taoiot.netty.mqtt.NettyMqttHandler;
import com.github.taoroot.taoiot.netty.tcp.core.PackageFactory;
import com.github.taoroot.taoiot.netty.tcp.core.PackageHandler;
import com.github.taoroot.taoiot.netty.tcp.core.PackageHandlerProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Configuration
@EnableConfigurationProperties(NettyProperties.class)
@ComponentScan({
        "com.github.taoroot.taoiot.netty.tcp.impl",
        "com.github.taoroot.taoiot.netty.mqtt.impl"
})
@Log4j2
public class NettyAutoConfiguration {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private NettyProperties nettyProperties;

    private EventLoopGroup mainGroup;

    private EventLoopGroup subGroup;

    private EventLoopGroup handlerGroup;

    @PostConstruct
    private void init() {
        if (nettyProperties.isEnableMqtt() || nettyProperties.isEnableTcp()) {
            mainGroup = new NioEventLoopGroup(1);
            subGroup = new NioEventLoopGroup();
            handlerGroup = new NioEventLoopGroup();
        }
        if (nettyProperties.isEnableMqtt()) {
            startMqtt();
        }

        if (nettyProperties.isEnableTcp()) {
            startTcp();
        }
    }

    @SneakyThrows
    private void startMqtt() {
        // 扫描mqtt业务处理器
        applicationContext.getBeansOfType(MqttHandler.class).values().forEach(MqttHandlerProcessor::registerHandler);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MqttEncoder.INSTANCE);
                        ch.pipeline().addLast(new MqttDecoder());
                        ch.pipeline().addLast(new IdleStateHandler(45, 0, 0, TimeUnit.SECONDS));
                        ch.pipeline().addLast(handlerGroup, new NettyMqttHandler());
                    }
                });
        serverBootstrap.bind(nettyProperties.getMqttPort()).sync();
        log.info("Netty on port: {} (mqtt) ", nettyProperties.getMqttPort());
    }

    @SneakyThrows
    private void startTcp() {
        // 扫描tcp协议包
        PackageFactory.scanPackage("com.github.taoroot.taoiot.netty.tcp.pkg");
        // 扫码tcp处理器
        applicationContext.getBeansOfType(PackageHandler.class).values().forEach(PackageHandlerProcessor::registerHandler);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                    }
                });
        serverBootstrap.bind(nettyProperties.getTcpPort()).sync();
        log.info("Netty on port: {} (tcp) ", nettyProperties.getTcpPort());
    }


    /**
     * 关闭Netty
     */
    @SneakyThrows
    private void stop() {
        if (mainGroup != null) {
            mainGroup.shutdownGracefully().sync();
        }
        if (subGroup != null) {
            subGroup.shutdownGracefully().sync();
        }
        if (handlerGroup != null) {
            handlerGroup.shutdownGracefully().sync();
        }
        log.info("Netty stopped");
    }
}
