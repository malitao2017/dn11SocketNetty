package oldNetty3.o2fix.f1basic;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class MessageServerHandler extends SimpleChannelUpstreamHandler{
	   private static final Logger logger = Logger.getLogger(  
	            MessageServerHandler.class.getName());  
	   
	    @Override  
	    public void messageReceived(  
	            ChannelHandlerContext ctx, MessageEvent e) {  
	        e.getChannel().write(e.getMessage());
	    }  
	   
	    @Override  
	    public void exceptionCaught(  
	            ChannelHandlerContext ctx, ExceptionEvent e) {  
	        logger.log(  
	                Level.WARNING,  
	                "Unexpected exception from downstream.",  
	                e.getCause());  
	        e.getChannel().close();  
	    }

		@Override
		public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
				throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			// TODO Auto-generated method stub
		}
	    
	    
}
