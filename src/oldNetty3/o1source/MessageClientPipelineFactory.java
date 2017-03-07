/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:19:10 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/** 
 *  对于编码和解码Handler，复用MessageEncoder和MessageDecoder即可。

 * 创建日期：2015年12月15日 下午4:19:10 
 * @author malitao
 */
public class MessageClientPipelineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		// ChannelPipeline pipeline = pipeline();／／原来的没有了
		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("decoder", new MessageDecoder());
		pipeline.addLast("encoder", new MessageEncoder());
		pipeline.addLast("handler", new MessageClientHandler());

		return pipeline;
	}
}
