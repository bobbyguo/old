/**
 * @(#)Debug.java Jul 28, 2009 10:06:54 PM
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.debug;

/**
 * 
 * @description:����ʱ��̨��ӡ�࣬����ͳһ���ƺ�̨��Ϣ�Ĵ�ӡ�����ڹ���
 * @author  :Bobby <br>
 * @version :1.0    <br>
 * @date    :Jul 28, 2009 10:09:46 PM <br>
 */
public class Debug {
	/*
	 * flag���ڱ����Ϣ�Ƿ��ӡ�����Ϊtrue���ӡ�����򲻴�ӡ��
	 */
	private static boolean flag = true;
	
	/**
	 * ���ڴ�ӡ��Ϣ�Ĺ���������
	 * @param o
	 */
	public static void println(Object o){
		if(flag){
		System.out.println(o);
		}
	}

}
