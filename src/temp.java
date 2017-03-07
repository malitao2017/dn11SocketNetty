/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn11SocketNetty
 * 描述信息: 
 * 创建日期：2015年12月17日 上午10:14:26 
 * @author malitao
 * @version 
 */

/** 
 *  
 * 创建日期：2015年12月17日 上午10:14:26 
 * @author malitao
 */
public class temp {

	public static void main(String[] args) {
//		f1();
//		f2();
		f3();
	}
	public static void f1(){
		int i=1;
		if(i==1)
			return ;
		System.out.println("11111111111");
	}
	public static void f2(){
		System.out.println("22222222222");
	}
	public static void f3(){
		try{
//			throw new NullPointerException();
			throw new RuntimeException();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("错误："+e.fillInStackTrace());
		}
	}
}
