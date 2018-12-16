package com.aisino.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyServer implements IServer {
    @Override
    public void serve(int port) throws Exception {

        ByteBuf byteBuf = Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8"));
        final ByteBuf buf = Unpooled.unreleasableBuffer(byteBuf);
        EventLoopGroup group = new OioEventLoopGroup();
        try {

            //辅助工具类，用于服务器通道的一系列配置
            ServerBootstrap b = new ServerBootstrap();        //1

            b.group(group)                                    //2
            ////指定NIO的模式
                    .channel(OioServerSocketChannel.class)
            .localAddress(new InetSocketAddress(port))
            .childHandler(new ChannelInitializer<SocketChannel>() {//3
                @Override
                public void initChannel(SocketChannel ch)throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {            //4
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);//5
                        }
                    });
                }


            });
            ChannelFuture f = b.bind().sync();  //6
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();        //7
        }

    }
}
