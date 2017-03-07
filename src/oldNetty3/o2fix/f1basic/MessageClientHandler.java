package oldNetty3.o2fix.f1basic;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
/**
 * 在channelConnected方法中，当连接到服务器的时候，就开始发送256字节的字符串。当messageReceived接收到服务器的消息时，又把消息发送给服务器。 
 *  
 * 创建日期：2015年12月15日 下午2:55:48 
 * @author malitao
 */
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
        System.out.println("messageReceived send message "+e.getMessage());  
        try {
        	Thread.sleep(3000);
        } catch (InterruptedException e1) {
        	// TODO Auto-generated catch block
        	e1.printStackTrace();
        }
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
