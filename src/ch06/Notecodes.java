package ch06;

public class Notecodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "aaa";
		String b = new String(a);
		System.out.println(a == "aaa");
		System.out.println(a == b);
		
		if('c' == Character.valueOf('c')) System.out.println(true);
		if(Character.valueOf('b') == Character.valueOf('b')) System.out.println(true);
		
		char bbb = Character.valueOf('b');
		Character bB1 = bbb;
		Character bB2 = Character.valueOf('b');
		if(bbb == bB2) System.out.println(true);
		if(bB1 == bB2) System.out.println(true);
		
	}

}
