package com.example.nio2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Date 2020/2/25
 * @Version V1.0
 * @Author Mads
 **/
public class EchoServerHandler1 extends ChannelInboundHandlerAdapter  {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("bbb");
        ByteBuf in = (ByteBuf) msg;
        System.out.println("服务端--> 接收到信息: "+in.toString(CharsetUtil.UTF_8));

        if (msg instanceof HttpRequest) {
            System.out.println("aaaa");
            // 请求，解码器将请求转换成HttpRequest对象
            HttpRequest request = (HttpRequest) msg;

            // 获取请求参数
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
            String name = "netty";
            if(queryStringDecoder.parameters().get("name") != null) {
                name = queryStringDecoder.parameters().get("name").get(0);
            }

            // 响应HTML
            String responseHtml = "<html><body>Hello, " + name + "</body></html>";
            byte[] responseBytes = responseHtml.getBytes("UTF-8");
            int contentLength = responseBytes.length;

            // 构造FullHttpResponse对象，FullHttpResponse包含message body
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseBytes));
            response.headers().set("Content-Type", "text/html; charset=utf-8");
            response.headers().set("Content-Length", Integer.toString(contentLength));

            ctx.writeAndFlush(response);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest req) throws Exception {
//        System.out.println("ccc");
//        // 获取请求的uri
//        String uri = req.uri();
//        Map<String,String> resMap = new HashMap<>();
//        resMap.put("method",req.method().name());
//        resMap.put("uri",uri);
//
//        System.out.println(resMap);
//    }


}
