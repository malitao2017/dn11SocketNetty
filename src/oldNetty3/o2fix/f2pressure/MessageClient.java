package oldNetty3.o2fix.f2pressure;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class MessageClient {
	public static void main(String[] args) throws Exception {  
        // Parse options.  
//		for(int i=0;i<1;i++){
//			create();
//		}
		create();
    }  
	public static void create(){
        String host = "192.168.1.49";  
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
//        for(int i=0;i<10;i++){
//            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));  
//            // Wait until the connection is closed or the connection attempt fails.  
//            future.getChannel().getCloseFuture().awaitUninterruptibly();  
//        }
        int testN = 667;
        ChannelFuture future[] = new ChannelFuture[testN];
        for (int i = 0; i < testN; i++) {
        future[i] = bootstrap.connect(new InetSocketAddress(host, port));
        }
        for (int i = 0; i < testN; i++) {
        future[i].getChannel().getCloseFuture().awaitUninterruptibly();
        }
        // Shut down thread pools to exit.  
        //  future.getChannel().write("我们都是中国人 啊啊！");   
        bootstrap.releaseExternalResources();  
	}
}
