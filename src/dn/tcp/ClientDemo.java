package dn.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientDemo {
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientDemo cd = new ClientDemo();
		cd.open();
	}
	public void open() throws UnknownHostException, IOException{
		//发起 localhost:8000 的连接
		//若成功就创建实例socket,不成功会抛出异常
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 8000);
		//服务器到客户端的流
		InputStream in = socket.getInputStream();
		//客户端到服务器的流
		OutputStream out = socket.getOutputStream();
		//这里的Reader和Writer是针对客户端的控制台而言的
		//负责从客户端的控制台读取信息输出到服务器端
		new Reader(out).start();
		//负责从服务器读取信息，输出到客户端的控制台
		new Writer(in).start();
	}
	/**
	 * 守护线程
	 * 成员内部类，负责将控制台的信息输出到服务器端
	 * @author malitao
	 *
	 */
	class Reader extends Thread{
		OutputStream out;
		public Reader(OutputStream out){
			this.out = out;
			//守护线程
			setDaemon(true);
		}
		@Override
		public void run() {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			try{
				while(true){
					out.write(s.nextLine().trim().getBytes());//读取控制台
					out.write('\n');
					out.flush();//发送到服务器端
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 成员内部类
	 * 服务从服务器读取信息输出到控制台
	 * @author malitao
	 *
	 */
	class Writer extends Thread{
		InputStream in;
		public Writer(InputStream in){
			this.in = in;
		}
		@Override
		public void run() {
			int b;
			try{
				while( (b = in.read()) != -1) {
					System.out.write(b); //控制台会处理编码问题
//					System.out.println(b);//这个是错的，控制台处理不了
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
