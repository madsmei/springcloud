package com.example.nio2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


/**
 * 服务端
 * 1.创建一个ServerBootstrap的实例引导和绑定服务器。
 * 2.创建并分配一个NioEventLoopGroup实例以进行事件的处理，比如接受连接以及读写数据。
 * 3.指定服务器绑定的本地的InetSocketAddress。
 * 4.使用一个EchoServerHandler的实例初始化每一个新的Channel。
 * 5.调用ServerBootstrap.bind()方法以绑定服务器。
 */
@Slf4j
@Component
public class EchoServer {
    /**
     * NioEventLoop并不是一个纯粹的I/O线程，它除了负责I/O的读写之外
     * 创建了两个NioEventLoopGroup，
     * 它们实际是两个独立的Reactor线程池。
     * 一个用于接收客户端的TCP连接，
     * 另一个用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等。
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    /**
     * 启动服务
     * @param hostname
     * @param port
     * @return
     * @throws Exception
     */
    public ChannelFuture start(String hostname, int port) throws Exception {

        ChannelFuture f = null;
        try {
            //ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .localAddress(new InetSocketAddress(hostname,port))
//                    .localAddress(port)
                    .option(ChannelOption.SO_BACKLOG, 1024)        // 设置tcp缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32*1024)    // 设置发送缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32*1024)    // 这是接收缓冲大小
                    .option(ChannelOption.SO_KEEPALIVE, true)    // 保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                              //为监听客户端read/write事件的Channel添加用户自定义的ChannelHandler
                            socketChannel.pipeline().addLast(new EchoServerHandler1());
                        }
                    });

            //绑定端口启动
            f = b.bind(port).sync();
            System.out.println("======EchoServer启动成功!!!========="+hostname+" "+port);

            //等待关闭
            f.channel().closeFuture().sync();

            channel = f.channel();
//            System.out.println("======EchoServer启动成功!!!========="+hostname+" "+port);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (f != null && f.isSuccess()) {
                System.out.println("Netty server listening " + hostname + " on port " + port + " and ready for connections...");
            } else {
                System.out.println("Netty server start up Error!");
            }

//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }
        return f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        System.out.println("Shutdown Netty Server...");
        if(channel != null) { channel.close();}
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        System.out.println("Shutdown Netty Server Success!");
    }
}
