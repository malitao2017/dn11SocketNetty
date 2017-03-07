package dn.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TCP 服务器
 * 客户端命令： telnet localhost 8000
 * @author malitao
 *
 */
public class ServerDemo {

	public static void main(String[] args) throws IOException{
		ServerDemo server = new ServerDemo();
		//常规
//		server.start();
		//使用线程池
		server.startPool();
//		System.out.println("你吃点啥？\n".getBytes());
	}
	
	public void start() throws IOException{
		//绑定端口 8000，不能重复绑定
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8000);
		while(true){
			System.out.println("等待客户的链接。。。");
			//开启 ServerSocket实例监听，等待客户端链接
			//若有客户端连接，就获得客户端的套接字（socket）实例
			Socket socket = ss.accept();
			System.out.println("链接客户成功："+socket.getInetAddress());
			new Service(socket).start();
		}
	}
	
	/**
	 * 使用线程池和消息队列实现
	 * @throws IOException
	 */
	public void startPool() throws IOException{
		//绑定端口 8000，不能重复绑定
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8000);
		
		//使用线程池和消息队列
		ServerPool sp = new ServerPool(50, 10000);
		while(true){
			System.out.println("等待客户的链接。。。");
			//开启 ServerSocket实例监听，等待客户端链接
			//若有客户端连接，就获得客户端的套接字（socket）实例
			Socket socket = ss.accept();
			System.out.println("链接客户成功："+socket.getInetAddress());
//			new Service(socket).start();
			sp.execute(new Service(socket));
		}
	}
	
	
	//服务器端,内部成员类
	class Service extends Thread{
		Socket socket;
		public Service(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try{
				//客户端传来的流
			    InputStream in = socket.getInputStream();
			    //服务器向客户端输出的流
			    OutputStream out = socket.getOutputStream();
			    //向客户端写信息，不能丢 \n，其是每一行的结束语
			    out.write("你吃点啥？\n".getBytes());
			    out.flush();
			    
			    //循环扫描客户端写回到服务器，判断并回复
			    @SuppressWarnings("resource")
				Scanner s = new Scanner(in);
			    while(true){
			    	String str = s.nextLine().trim();
			    	if("粗面".equals(str)){
			    		out.write("木有粗面！".getBytes());
			    		out.write('\n');
			    		out.flush();
			    	}else if("包子".equals(str)){
			    		out.write("有！给你".getBytes());
			    		out.write('\n');
			    		out.flush();
			    		break;
			    	}else{
			    		out.write("你说啥".getBytes());
			    		out.write('\n');
			    		out.flush();
			    	}
			    }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 使用线程池和任务队列的形式
	 * 
	 * @author malitao
	 *
	 */
	class ServerPool{
		ExecutorService executor;
		public ServerPool(int maxPoolSize,int queueSize){//50 10000
//			executor = new ThreadPoolExecutor(corePoolSize, 
//					maximumPoolSize, keepAliveTime, unit, workQueue)
			executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 
					maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
		}
		public void execute(Runnable task){
			executor.execute(task);
		}
	}
	
}
