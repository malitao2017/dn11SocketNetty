package oldNetty3.o2fix.f2pressure;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class MessageClientHandler extends SimpleChannelUpstreamHandler {  
	   
    private static final Logger logger = Logger.getLogger(  
            MessageClientHandler.class.getName());  
   
   
    @Override  
    public void channelConnected(  
            ChannelHandlerContext ctx, ChannelStateEvent e) {  
        e.getChannel().write(getAString(256));  
    }  
   
    @Override  
    public void messageReceived(  
            ChannelHandlerContext ctx, MessageEvent e) {  
        // Send back the received message to the remote peer.  
 //       System.out.println("messageReceived send message "+e.getMessage());  
//        try {  
//            Thread.sleep(1000*3);  
//        } catch (Exception ex) {  
//            ex.printStackTrace();  
//        }  
        e.getChannel().write(e.getMessage());  
    }  
   
    @Override  
    public void exceptionCaught(  
            ChannelHandlerContext ctx, ExceptionEvent e) {  
        // Close the connection when an exception is raised.  
        logger.log(  
                Level.WARNING,  
                "Unexpected exception from downstream.",  
                e.getCause());  
        e.getChannel().close();  
    }  
    public String getAString(int size){
    	StringBuilder str = new StringBuilder("");
    	for(int i=0;i<size;i++){
    		str.append("a");
    	}
    	return str.toString();
    }
}  
