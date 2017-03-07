/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午3:55:42 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/** 
 *  
 * 创建日期：2015年12月15日 下午3:55:42 
 * @author malitao
 */
@ChannelPipelineCoverage("all")
public class MessageServerHandler extends SimpleChannelUpstreamHandler {
 
    private static final Logger logger = Logger.getLogger(
            MessageServerHandler.class.getName());
 
    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) {
        if (!(e.getMessage() instanceof String)) {
            return;//(1)
        }
        String msg = (String) e.getMessage();
        System.err.println("server got msg:"+msg);
        e.getChannel().write(msg);//(2)
    }
 
    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
}
