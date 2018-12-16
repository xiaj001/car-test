package com.aisino.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.util.Date;

/**
 * @author jun.xia
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
        System.out.println("server receive message :"+ msg);
        ctx.channel().writeAndFlush("yes server already accept your message" + msg);
        ctx.close();
       /* ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req , "UTF-8");
        System.out.println("the time server receive order : " + body);
        String currentTime = "QUERY TIME ORDER ".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        ByteBuf res = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(res);*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("【channelActive】。。。");
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        /**
         * 将消息发送队列中的消息写入到 SocketChannel 中发送给对方。
         * 从性能角度考虑，为了防止频繁的 唤醒 Selector 进行消息发送
         * Netty 的write 方法并不直接将消息 写入到 SocketChannel 中，
         * 调用write 方法只是把待发的消息放到发送缓冲数组中，再通过调用 flush方法
         * 将发送缓冲区中的消息全部写入到 SocketChannel 中
         *
         */
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){

        System.out.println("【exception is general】");
        ctx.close();

    }
}
