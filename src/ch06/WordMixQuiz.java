package ch06;
import java.util.Scanner;

class Quiz {
	final int LIMIT_SEC = 10;;
	private String word[] = {
		"lovely",
		"sunny",
		"present",
		"cookie",
		"extract",
		"nation",
		"break",
		"connect"
	};
	public Quiz() {
		System.out.println(LIMIT_SEC + "초 안에 단어를 맞추세요!");
	}
	
	public boolean[] start(String[] mixedWord, Scanner scanner) {
		boolean[] corrOrFail = new boolean [mixedWord.length];
		for(int i = 0 ; i < corrOrFail.length ; i++) corrOrFail[i] = false;
		
		
		for(int i = 0 ; i < mixedWord.length ; i++) {
			System.out.println(mixedWord[i]+ "");
			System.out.print(">>");
			
//			int startTimeMillis = (int) System.currentTimeMillis();
			String input = scanner.nextLine().trim();
	//		int passedTimeSeconds = ((int) System.currentTimeMillis() - startTimeMillis) / 100;
			
			if(input.equals("그만")) return corrOrFail;
			else if (input.equals(word[i])) {
				corrOrFail[i] = true;
				System.out.println("성공!!!");
			}
			else {
				System.out.println("실패!!! " + word[i] + " 입니다.");
			}
			
		}
			
		
		return corrOrFail;
		
	}
	
	public String[] MixSpelling() {
		String mixedWord[] = new String[word.length];
		for (int i = 0 ; i < word.length ; i++) {
			char[] alphabetFromWord = word[i].toCharArray();
			
			int replaceCharIndex1 = (int) (Math.random()*alphabetFromWord.length);
			int replaceCharIndex2 = (int) (Math.random()*alphabetFromWord.length);
			while(true) {
				if(replaceCharIndex1 == replaceCharIndex2) {
					replaceCharIndex2 = (int) (Math.random()*alphabetFromWord.length);
					continue;
				}
				break;
			}
			
			char t = alphabetFromWord[replaceCharIndex1];
			alphabetFromWord[replaceCharIndex1] = alphabetFromWord[replaceCharIndex2];
			alphabetFromWord[replaceCharIndex2] = t;
			
			mixedWord[i] = String.valueOf(alphabetFromWord);
		}
		return mixedWord;
	}
	
	public void printScore(boolean[] corrOrFail) {
		int cnt = 0;
		for (boolean b : corrOrFail) if(b) cnt++;
		System.out.println(corrOrFail.length + "개 중 "+ cnt + "개 정답");
	}
	
}
public class WordMixQuiz {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Quiz q = new Quiz();
		
		q.printScore(q.start(q.MixSpelling(), scanner));
		
		scanner.close();
	}

}
