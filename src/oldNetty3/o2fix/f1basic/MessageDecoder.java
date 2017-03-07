package oldNetty3.o2fix.f1basic;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * 对应前面的编码，先读取4字节的int.如发现不够4字节，则直接返回null,累积到下一次读取。如果发现读取的数据长度不够，则累积到下一次读取。 
 *  
 *  MessageDecoder继承自FrameDecoder，FrameDecoder是Netty codec包中的辅助类，它是个ChannelUpstreamHandler，decode方法是FrameDecoder子类需要实现的。在上面的代码中，有：
(1)检查ChannelBuffer中的字节数，如果ChannelBuffer可读的字节数少于4,则返回null等待下次读事件。
(2)继续检查ChannelBuffer中的字节数，如果ChannelBuffer可读的字节数少于dataLength + 4，则返回null等待下次读事件。
(3)越过dataLength的字节。
(4)构造解码的字符串返回。
 * 创建日期：2015年12月15日 下午3:03:46 
 * @author malitao
 */
public class MessageDecoder extends FrameDecoder{

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		// TODO Auto-generated method stub
		if (buffer.readableBytes() < 4) {
			return null;// (1)
		}
		int dataLength = buffer.getInt(buffer.readerIndex());
		if (buffer.readableBytes() < dataLength + 4) {
			return null;// (2)
		}

		buffer.skipBytes(4);// (3)
		byte[] decoded = new byte[dataLength];
		buffer.readBytes(decoded);
		String msg = new String(decoded);// (4)
		return msg;
	}

}
