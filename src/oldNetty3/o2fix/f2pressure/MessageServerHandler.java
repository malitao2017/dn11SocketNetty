package oldNetty3.o2fix.f2pressure;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class MessageServerHandler extends SimpleChannelUpstreamHandler{
	  private static int num = 0;
	  private static int count = 0;
	  private static boolean isStart = false;
	   private static final Logger logger = Logger.getLogger(  
	            MessageServerHandler.class.getName());  
	   
	    @Override  
	    public void messageReceived(  
	            ChannelHandlerContext ctx, MessageEvent e) {  
//	        if (!(e.getMessage() instanceof String)) {  
//	            return;//(1)  
//	        }  
	     //   String msg = (String) e.getMessage();  s
	        e.getChannel().write(e.getMessage());//(2)  
	        if(num>=2000-5){
	        	count++;
	        	if(count%10000 == 0){
	        		System.out.println(count);
	        	}
	        	if(!isStart){
	        		isStart= true;
	        		 new Thread(new Runnable() {
	 	 				
	 	 				@Override
	 	 				public void run() {
	 	 					// TODO Auto-generated method stub
	 	 					try {
	 							Thread.sleep(60000);
	 							System.exit(0);
	 						} catch (InterruptedException e) {
	 							// TODO Auto-generated catch block
	 							e.printStackTrace();
	 						}
	 	 				}
	 	 			}).start();
	        	}
	        	
	        }
	       
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
			num++;
			System.out.println(num);
		}
	    
	    
}
