package ch06;
import java.util.Scanner;
//Page396, P06, String method
public class RotateString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("문자열을 입력하세요. 빈 칸이 있어도 되고 영어 한글 모두 됩니다.");
		String line = scanner.nextLine();
		for(int i = 1 ; i < line.length()+1 ; i++) {
			System.out.print(line.substring(i));
			System.out.println(line.substring(0, i));
		}
		
	}

}
