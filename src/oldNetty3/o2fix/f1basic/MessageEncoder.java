package oldNetty3.o2fix.f1basic;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
/**
 * 开始编码：将要发送的字符串转换成字节，数据包头是一个4字节的int,后面就是字符串byte. 
 *  
 * 创建日期：2015年12月15日 下午2:56:08 
 * @author malitao
 */
public class MessageEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		// TODO Auto-generated method stub
		if (!(msg instanceof String)) {
			return msg;// (1)
		}
		String res = (String) msg;
		byte[] data = res.getBytes();
		int dataLength = data.length;
		ChannelBuffer buf = ChannelBuffers.dynamicBuffer();// (2)
		buf.writeInt(dataLength);
		buf.writeBytes(data);
		return buf;
	}
   

}  
