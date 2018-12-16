package com.aisino.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author jun.xia
 */
public class TimeClient {


    public static void main(String[] args) throws Exception {
        int port = 8888;
        new TimeClient().connect(port,"127.0.0.1");
    }



    public void connect(int port ,String host)throws Exception{

        //配置客户端线程组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }

                    });


            //发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();

            f.channel().writeAndFlush("QUERY TIME ORDER !"+Thread.currentThread().getName()+":--->:"+Thread.currentThread().getId());

            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();

        }
    }


}


