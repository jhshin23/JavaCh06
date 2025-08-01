package ch06;

import java.util.StringTokenizer;

//Page390, 이론문제 3,4번
public class Example {

	public static void main(String[] args) {
//		StringTokenizer st = new StringTokenizer("a=3,b=5,c=6", ",");
//		while(st.hasMoreTokens())
//			System.out.println(st.nextToken());
		//3번
		String str = "a=3,b=5,c=6";
		String splitStr[] = str.split(",");
		for(String s : splitStr) System.out.println(s);
		//4번
		StringTokenizer st = new StringTokenizer("a=3,b=5,c=6", ",=");
		while(st.hasMoreTokens())
			System.out.println(st.nextToken());
		
		
	}

}
