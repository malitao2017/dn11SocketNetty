package oldNetty3.o2fix.f2pressure;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class MessageServer {
	 public static void main(String[] args) throws Exception {  
	        // Configure the server.  
	        ServerBootstrap bootstrap = new ServerBootstrap(  
	                new NioServerSocketChannelFactory(  
	                        Executors.newCachedThreadPool(),  
	                        Executors.newCachedThreadPool()));  
	   
	        // Set up the default event pipeline.  
	       // bootstrap.setPipelineFactory(new MessageServerPipelineFactory()); 
	        bootstrap.getPipeline().addLast("decoder", new MessageDecoder());
	        bootstrap.getPipeline().addLast("encoder", new MessageEncoder());
	        bootstrap.getPipeline().addLast("handler", new MessageServerHandler());
	        // Bind and start to accept incoming connections.  
	        bootstrap.bind(new InetSocketAddress(9550));  
	    }  
}
