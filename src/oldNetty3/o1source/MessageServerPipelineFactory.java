/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:14:08 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/** 
 *  创建了3个ChannelHandler，需要将他们注册到ChannelPipeline，而ChannelPipeline又是和Channel对应的（是全局单例还是每个Channel对应一个ChannelPipeline实例依赖于实现）。
 *  可以实现ChannelPipeline的工厂接口 ChannelPipelineFactory实现该目的。MessageServerPipelineFactory的代码
 *  
 * 创建日期：2015年12月15日 下午4:14:08 
 * @author malitao
 */
public class MessageServerPipelineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		// ChannelPipeline pipeline = pipeline();／／原来的没有了
		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("decoder", new MessageDecoder());
		pipeline.addLast("encoder", new MessageEncoder());
		pipeline.addLast("handler", new MessageServerHandler());

		return pipeline;
	}
}