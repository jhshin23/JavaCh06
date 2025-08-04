package ch06;
//Page391, T06
public class WrapperCodeQuiz {

	public static void main(String[] args) {
		//1
		String s1 = Integer.toString(20);
		//2
		double d = Double.parseDouble("35.9");
		//3
		boolean b = Boolean.parseBoolean("true");
		//4
		String s2 = Integer.toBinaryString(30);
		//5
		String c = Character.toString('c');
		
		//답 확인
		System.out.println(s1.concat(" is String."));
		System.out.println(d+100);
		if(b) System.out.println(s2 + c);
	}

}
