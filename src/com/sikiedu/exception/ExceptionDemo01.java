package com.sikiedu.exception;

import java.util.ArrayList;

public class ExceptionDemo01 {
	public static void main(String[] args) {
//		
//		try {
//			//目前为空 未new不能使用
//			ArrayList<Integer> list =null;
//		
//			list.add(34);
//			list.add(244);
//		}catch(NullPointerException e) {
//		/*	System.out.println("出现空指针");*/
//			throw e;
//		}
//		
		
	/*	try {
			test(20);
			test(0);
			test(200);
			test(-90);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	public static void test(int age) throws Exception{
	if(age>=0&&age<20) {
		System.out.println("未成年");
	}else if(age>=20&&age<=150) {
		System.out.println("成年");
	}else {
		throw new Exception("年龄输入不合法");
	}
	}	
}

	
	
	
	