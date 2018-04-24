package com.xitao.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.util.stream.IntStream;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(7777));
        ByteBuffer inputBuffer = ByteBuffer.allocate(10);
        for(int i=0;i<1000;i++) {
            byte[] bytes = ("aa"+i).getBytes();
            Thread.sleep(1000);
            inputBuffer.put(bytes);

            socketChannel.write(inputBuffer);
            inputBuffer.clear();
        }
    }

}
