package com.example.netty.test.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

public class ByteBufTest {

    public void heapBUff(){
        ByteBuf heapBuf= Unpooled.copiedBuffer("lihao", CharsetUtil.UTF_8);

        if(heapBuf.hasArray()){
            byte[] array=heapBuf.array();
            //计算第一字节偏移量
            int offset=heapBuf.arrayOffset()+heapBuf.readerIndex();
            int length=heapBuf.readableBytes();
            handle(array,offset,length);
        }


    }


    public void  directBuff(){
        ByteBuf directBuff=Unpooled.directBuffer(1024);
        byte[] bytes="徐蓉蓉\rnddss".getBytes();
        directBuff.writeBytes(bytes,0,bytes.length);
        directBuff.markReaderIndex();
        directBuff.readerIndex(3);
        int index=directBuff.forEachByte(ByteProcessor.FIND_CR);
        if(!directBuff.hasArray()){
            int length=directBuff.readableBytes();
            ByteBuf byteBuf1=directBuff.slice(0,4);
            System.out.println(byteBuf1.toString());
            byte[] bytes1=new byte[length];
            directBuff.getBytes(directBuff.readerIndex(),bytes1);
            handle(bytes1,0,length);
        }

    }

    public void byteBuffAllocate(){


    }

    private void handle(byte[] array, int offset, int length) {
        String str=new String(array,offset,length);
        System.out.println("解码出来的字符是："+str);
    }


    public static void main(String[] args) {
        ByteBufTest byteBufTest=new ByteBufTest();
        byteBufTest.heapBUff();
        byteBufTest.directBuff();

    }
}
