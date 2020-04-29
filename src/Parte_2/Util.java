package Parte_2;

import java.util.*;
public class Util { 
	public static int max(int a, int b) { 
		if( a > b ) return a; return b; 
	} 
	public static void mySleep(int time) { 
		try { Thread.sleep(time);
		}catch(InterruptedException e){ } 
		} 
	public static void myWait(Object obj) { 
		System.out.println("esperando"); 
		try{ obj.wait(); 
		}catch(InterruptedException e){ } 
		} 
}