package ch06;

import java.util.Random;

//Page392, P11, 이론문제 11번
public class RandomQuizCodeSnippet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1
		for (int i = 0 ; i < 10 ; i++) {
			System.out.print(((int)(Math.random() * 156)) + 100);
			System.out.print(" ");
		}
		System.out.println();
		//2
		Random r = new Random();
		for (int i = 0 ; i < 10 ; i++) {
			System.out.print(r.nextInt(156) + 100);
			System.out.print(" ");
		}
	}

}
