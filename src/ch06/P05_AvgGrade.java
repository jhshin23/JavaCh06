package ch06;
import java.util.Scanner;
import java.util.StringTokenizer;

//Page395, P05, split(), StringTokenizer
class CalcAvg {
	static public double getAvg(int [] scoreArray) {
		if(scoreArray[0] == -1)
			return -1;
		double sum = 0;
		for (int i = 0 ; i < scoreArray.length ; i++) 
			sum += scoreArray[i];
		
		return sum / scoreArray.length;
	}
}

public class P05_AvgGrade {
	
	static public int[] getScoreArray(String[] gradeArray) {
		int scoreArray[] = new int[gradeArray.length];
		int errorArray[] = { -1 };
		for(int i = 0 ; i < gradeArray.length ; i++) {
			String b = gradeArray[i].toUpperCase();
			switch(b) {
			case "A":
				scoreArray[i] = 100;
				break;
			case "B":
				scoreArray[i] = 90;
				break;
			case "C":
				scoreArray[i] = 80;
				break;
			case "D":
				scoreArray[i] = 70;
				break;
			case "F":
				scoreArray[i] = 0;
				break;
				default:
					System.out.println("입력 오류: " + gradeArray[i]);
					return errorArray;
			}
		}
		return scoreArray;
		
	}
	
	static public boolean checkInput(String [] gradeArray) {
		for(String a : gradeArray) {
			String b = a.toUpperCase();
			if (b.equals("A") || b.equals("B") || b.equals("C")|| b.equals("D")|| b.equals("F"))
				continue;
			else {
				System.out.println("입력 오류: " + a);
				return false;
			}
		}
		return true;
	}
	
	static public String[] getGradeArray(String line) {
		String [] gradeArray = line.split(" ");
		for(int i = 0 ; i < gradeArray.length ; i++)
			gradeArray[i] = gradeArray[i];
		
		return gradeArray;
	}
	
	static public String[] getTokenArray(String line) {
		StringTokenizer st = new StringTokenizer(line, " ");
		String [] tokenGradeArray = new String[st.countTokens()];
		for (int i = 0 ; i < tokenGradeArray.length ; i++) 
			tokenGradeArray[i] = st.nextToken();
		
		return tokenGradeArray;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String line = null;
		while(true) {
			System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
			line = scanner.nextLine();
			if(line.equals("그만"))
				break;
			if(checkInput(getGradeArray(line))) { //(1)조건 split() 사용
				double avg = CalcAvg.getAvg(getScoreArray(getTokenArray(line)));
				if(avg == -1) {
					System.out.println("오류");
					continue;
				}
				System.out.println("평균은 " + avg); //(2)조건 StringTokenizer 사용
			}
		}
		
	}

}
