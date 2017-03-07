/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月15日 下午4:20:09 
 * @author malitao
 * @version 
 */
package oldNetty3.o1source;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/** 
 *  在写客户端例子时，我想像的代码并不是这样的，对客户端的代码我也没做过多的研究，
 *  所以也可能没有找到更好的解决方案。在上面的例子中，bootstrap.connect方法中会触发实际的连接操作，
 *  接着触发 MessageClientHandler.channelConnected，使整个过程运转起来。
 *  但是，我想要的是一个连接池，并且如何写数据也不应该在channelConnected中，
 *  这样对于动态的数据，只能在构造函数中传递需要写的数据了。
 *  但到现在，我还不清楚如何将连接池和 ChannelPipeline有效的结合起来。或许，这样的需求可以跨过Netty来实现。

 * 创建日期：2015年12月15日 下午4:20:09 
 * @author malitao
 */
public class MessageClient {
	 
    public static void main(String[] args) throws Exception {
        // Parse options.
        String host = "127.0.0.1";
        int port = 8080;
        // Configure the client.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new MessageClientPipelineFactory());
        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
        // Wait until the connection is closed or the connection attempt fails.
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        // Shut down thread pools to exit.
        bootstrap.releaseExternalResources();
    }
}
