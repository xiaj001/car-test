package com.aisino.netty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author jun.xia
 */
public class TimeServer {

    public static void main(String[] args) throws Exception {
        int port = 8888;
        new TimeServer().bind(port);
    }

    public void bind(int port) throws Exception{
        //配置服务端的NIO线程组
        /**
         * NioEventLoopGroup 是个线程组，它包含了一组NIO线程，专门用于网络事件的处理
         * 实际上，他们就是 Reactor 线程组。这里创建两个的原因是 一个用于服务端接收客户端的连接
         * 另一个用于socketChannel 的网络读写
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            /**
             * ServerBootstrap 是netty 用于启动NIO 服务端的辅助启动类，
             * 目的是降低服务端的 开发复杂度
             */
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)

                    //NioServerSocketChannel 对于与 JDK NIO 类库中的ServerSocketChannel
                    .channel(NioServerSocketChannel.class)

                    //配置NioServerSocketChannel 的 TCP 参数
                    .option(ChannelOption.SO_BACKLOG,1024)

                    //绑定IO事件的处理类，它的作用类似于 Reactor 模式中的Handler
                    //用于处理网络IO事件
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeServerHandler());
                        }
                    });

            //绑定端口，同步等待成功。
            ChannelFuture f = b.bind(port).sync();

            //等待服务监听端口关闭
            f.channel().closeFuture().sync();

        }finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }



    }
}
