/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:17:54 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/** 
 *  客户端是先发送数据到服务端（downstream事件流），然后是处理从服务端接收的数据（upstream事件流）。这里有个问题是，怎么把需要发送的数据送到downstream事件流里呢？这就用到了ChannelUpstreamHandler的channelConnected事件了。实现的 MessageClientHandler代码如下：
客户端程序和服务端程序处理模型上是很相似的，这里还是付上代码并作简要说明。

 * 创建日期：2015年12月15日 下午4:17:54 
 * @author malitao
 */
@ChannelPipelineCoverage("all")
public class MessageClientHandler extends SimpleChannelUpstreamHandler {
 
    private static final Logger logger = Logger.getLogger(
            MessageClientHandler.class.getName());
 
 
    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) {
        String message = "hello kafka0102";
        e.getChannel().write(message);
    }
 
    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) {
        // Send back the received message to the remote peer.
        System.err.println("client messageReceived send message： "+e.getMessage());
        try {
            Thread.sleep(1000*3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        e.getChannel().write(e.getMessage());
    }
 
    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
}
