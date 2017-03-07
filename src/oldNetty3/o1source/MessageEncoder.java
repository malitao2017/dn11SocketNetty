/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:10:24 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/** 
 *  
 *  MessageEncoder是个ChannelDownstreamHandler。对该类的注解如下：

(1)如果编码的msg不是合法类型，就直接返回该msg，之后OneToOneEncoder会调用 ctx.sendDownstream(evt);来调用下一个ChannelDownstreamHandler。对于该例子来说，这种情况是不应该出现的。
(2)开发者创建ChannelBuffer的用武之地就是这儿了，通常使用dynamicBuffer即可，表示得到的ChannelBuffer可动态增加大小。
(3)返回编码后的ChannelBuffer之后，OneToOneEncoder会调用Channels.write将数据写回客户端。

 * 创建日期：2015年12月15日 下午4:10:24 
 * @author malitao
 */
@ChannelPipelineCoverage("all")
public class MessageEncoder extends OneToOneEncoder {
 
    @Override
    protected Object encode(
            ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        if (!(msg instanceof String)) {
            return msg;//(1)
        }
 
        String res = (String)msg;
        byte[] data = res.getBytes();
        int dataLength = data.length;
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();//(2)
        buf.writeInt(dataLength);
        buf.writeBytes(data);
        return buf;//(3)
    }
}
