package ch06;
import java.util.Scanner;
//Page398, P10-(1)(2), 섞인 스펠링 퀴즈, 경과 시간 기록
class Quiz {
	final int LIMIT_SEC = 10;
	final int LEVEL = 10;
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
		boolean[] isCorrect = new boolean [mixedWord.length];
		for(int i = 0 ; i < isCorrect.length ; i++) isCorrect[i] = false;
		
		
		for(int i = 0 ; i < mixedWord.length ; i++) {
			System.out.println(mixedWord[i]);
			System.out.print(">>");
			
			double startTimeMillis = (double) System.currentTimeMillis();
			String input = scanner.nextLine().trim();
			double passedTimeSeconds = ((double) System.currentTimeMillis() - startTimeMillis) / 1000;
			if(input.equals("그만")) return isCorrect;
			else if (input.equals(word[i])) {
				if(passedTimeSeconds <= LIMIT_SEC) {
					isCorrect[i] = true;
					System.out.println("성공!!! " + passedTimeSeconds + "초 경과");
				}
				else System.out.println("실패!!! " + LIMIT_SEC + "초 초과. " + passedTimeSeconds + "초 경과");
			}
			else {
				System.out.println("실패!!! " + word[i] + " 입니다.");
			}
			
		}
			
		
		return isCorrect;
		
	}
	
	public String[] mixSpelling() {
		String mixedWord[] = new String[word.length];
		for (int i = 0 ; i < word.length ; i++) mixedWord[i] = word[i];
		
		for(int i = 0 ; i < LEVEL ; i++) {
			for (int j = 0 ; j < mixedWord.length ; j++) {
				char[] alphabetFromWord = mixedWord[j].toCharArray();
				
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
				
				mixedWord[j] = String.valueOf(alphabetFromWord);
			}
		}
		return mixedWord;
	}
	
	public void printScore(boolean[] isCorrect) {
		int cnt = 0;
		for (boolean b : isCorrect) if(b) cnt++;
		System.out.println(isCorrect.length + "개 중 "+ cnt + "개 정답");
	}
	
}
public class WordMixQuiz {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Quiz q = new Quiz();
		
		q.printScore(q.start(q.mixSpelling(), scanner));
		
		scanner.close();
	}

}
