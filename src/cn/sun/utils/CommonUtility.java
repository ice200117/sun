package cn.sun.utils;

import java.util.Date;

public class CommonUtility 
{
	public static void PrintInfo(String str)
	{
		Date dt=new Date();
		System.out.println(dt.getDay()+"日"+">>>>"+str);
	}
}
