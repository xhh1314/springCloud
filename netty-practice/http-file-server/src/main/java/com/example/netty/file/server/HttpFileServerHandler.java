package com.example.netty.file.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class HttpFileServerHandler  extends SimpleChannelInboundHandler<FullHttpRequest>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if(!request.decoderResult().isSuccess())
        {

            sendError(ctx,HttpResponseStatus.BAD_REQUEST);
            return;
        }
        if(request.method()!= HttpMethod.GET)
        {
            sendError(ctx,HttpResponseStatus.METHOD_NOT_ALLOWED);
                    return;
        }
        final String uri=request.uri();
        final String path=parseUri(uri);
        if(path==null)
        {
            sendError(ctx,HttpResponseStatus.NOT_FOUND);
            return;
        }
        File file=new File(path);
        if(file.isHidden()||!file.exists()){
            sendError(ctx,HttpResponseStatus.FORBIDDEN);
            return;
        }
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");
        Long length=randomAccessFile.length();
        HttpResponse response=new DefaultHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,length);
        setContentTypeHeader(response,file);
        ctx.writeAndFlush(response);
        ChannelFuture sendFileFuture=null;
        sendFileFuture=ctx.write(new ChunkedFile(randomAccessFile),ctx.newPromise());
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                if(total < 0)
                    System.err.println("Transfer progress: " + progress);
                else
                    System.out.println("Transfer progress: " + progress + "/" + total);
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                System.out.println("文件上传已经完成!");

            }
        });
        ChannelFuture lastContentFuture=ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);


    }




    private void sendError(ChannelHandlerContext ctx,HttpResponseStatus status){
        FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status, Unpooled.copiedBuffer("请求失败!"+status.toString(), CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html;charset=utf-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    private String parseUri(String uri){
        try {
            uri= URLDecoder.decode(uri,"utf8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri=URLDecoder.decode(uri,CharsetUtil.ISO_8859_1.name());
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }

        uri=uri.replace('/', File.separatorChar);

        return System.getProperty("user.dir")+File.separator+uri;


    }

    private static void setContentTypeHeader(HttpResponse response, File file){
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimetypesFileTypeMap.getContentType(file.getPath()));
    }

}
