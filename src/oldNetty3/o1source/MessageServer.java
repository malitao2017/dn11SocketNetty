/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:16:43 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/** 
 *  服务端程序就剩下启动代码了，使用Netty的ServerBootstrap三下五除二完成之。
稍加补充的是，该Server程序并不完整，它没有处理关闭时的资源释放，尽管暴力的来看并不一定需要做这样的善后工作。


 * 创建日期：2015年12月15日 下午4:16:43 
 * @author malitao
 */
public class MessageServer {
	 
    public static void main(String[] args) throws Exception {
        // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
 
        // Set up the default event pipeline.
        bootstrap.setPipelineFactory(new MessageServerPipelineFactory());
 
        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(8080));
 
    }
}