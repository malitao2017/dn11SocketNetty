package oldNetty3.o2fix.f1basic;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
/**
 * 
 * http://blog.csdn.net/huaye2007/article/details/8187995#
 * 原始的说明和用法参考：　oldNetty3.o1source;
 * 
 * 上面把bootstrap.setPipelineFactory(new MessageServerPipelineFactory()); 
 * 给注释掉了，原例子中服务端和客户端共用MessageServerPipelineFactory，
 * 但在添加handler的时候，添加的却是服务端的handler，所以是有问题的。
 * 要么写两个MessageServerPipelineFactory类，要么直接像上面那样添加。
 * 不能既有MessageServerPipelineFactory，又有bootstrap.getPipeline().addLast()这个方法。 
 * 
 *  
 * 创建日期：2015年12月15日 下午2:55:23 
 * @author malitao
 */
public class MessageClient {
	public static void main(String[] args) throws Exception {  
		create();
    }  
	public static void create(){
        String host = "127.0.0.1";  
        int port = 9550;  
        // Configure the client.  
        ClientBootstrap bootstrap = new ClientBootstrap(  
                new NioClientSocketChannelFactory(  
                        Executors.newCachedThreadPool(),  
                        Executors.newCachedThreadPool()));  
        // Set up the event pipeline factory.  
       // bootstrap.setPipelineFactory(new MessageServerPipelineFactory()); 
        bootstrap.getPipeline().addLast("decoder", new MessageDecoder());
        bootstrap.getPipeline().addLast("encoder", new MessageEncoder());
        bootstrap.getPipeline().addLast("handler", new MessageClientHandler());
        // Start the connection attempt.  
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));  
            // Wait until the connection is closed or the connection attempt fails.  
            future.getChannel().getCloseFuture().awaitUninterruptibly();  

        // Shut down thread pools to exit.  
      //  future.getChannel().write("我们都是中国人 啊啊！");   
        bootstrap.releaseExternalResources();  
	}
}
