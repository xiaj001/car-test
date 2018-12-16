package com.aisino.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOServer implements IServer{

    final BlockingQueue queue = new ArrayBlockingQueue(100);
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,20,10L,TimeUnit.MILLISECONDS,queue);

    @Override
    public void serve(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            final Socket accept = serverSocket.accept();
            System.err.println("Accepted connection from "+ accept);
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    OutputStream out;
                    try {
                        out = accept.getOutputStream();
                        out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));                            //4
                        out.flush();
                       /* accept.close();    */            //5

                    } catch (IOException e) {
                        e.printStackTrace();
                        /*try {
                           *//* accept.close();*//*
                        } catch (IOException ex) {
                            // ignore on close
                        }*/
                    }

                }
            });
        }
    }


    public static void main(String[] args) throws Exception {
        BIOServer server = new BIOServer();
        server.serve(8888);
    }
}
