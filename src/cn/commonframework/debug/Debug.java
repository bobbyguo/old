/**
 * @(#)Debug.java Jul 28, 2009 10:06:54 PM
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.debug;

/**
 * 
 * @description:调试时后台打印类，用于统一控制后台信息的打印，便于管理。
 * @author  :Bobby <br>
 * @version :1.0    <br>
 * @date    :Jul 28, 2009 10:09:46 PM <br>
 */
public class Debug {
	/*
	 * flag用于标记信息是否打印，如果为true则打印，否则不打印。
	 */
	private static boolean flag = true;
	
	/**
	 * 用于打印信息的公共方法。
	 * @param o
	 */
	public static void println(Object o){
		if(flag){
		System.out.println(o);
		}
	}

}
