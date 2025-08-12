package ch06;
import java.util.Scanner;
import java.util.Random;

//Page398, P09, hide word in 5x5 CrossWord
public class CrossWord {
	static private final int SIZE = 5;
	private StringBuffer board = new StringBuffer();
	public CrossWord() {

	}
	public void arrange(String word) { 
		Random r = new Random();
		String abc = "qwertyuiopasdfghjklzxcvbnm";
		board.delete(0, board.capacity());
		for(int i = 0 ; i < SIZE ; i++) {
			for(int j= 0 ; j < SIZE ; j++) {  
				board.append(abc.charAt(r.nextInt(abc.length())));
			}
		}  
		
		int [] wordArrangeSize = { 1, SIZE, SIZE-1, SIZE+1 };
		int sizeIndex = r.nextInt(wordArrangeSize.length);
		if (sizeIndex == 0) {
			int rowFirstChar = SIZE * (r.nextInt(SIZE));
			int columnFirstChar = r.nextInt(SIZE - word.length() + 1);
			for(int i = 0 ; i < word.length() ; i++)
				board.replace(rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex]), rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex])+1, word.substring(i, i+1));
			
			if(r.nextBoolean()) board.reverse();
		} else if (sizeIndex == 1) {
			int rowFirstChar = SIZE * (r.nextInt(SIZE - word.length() + 1));
			int columnFirstChar = r.nextInt(SIZE);
			for(int i = 0 ; i < word.length() ; i++)
				board.replace(rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex]), rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex])+1, word.substring(i, i+1));
			
			if(r.nextBoolean()) board.reverse();
		} else if (sizeIndex == 2) {
			int rowFirstChar = r.nextInt(word.length()-1, SIZE);//1 05 2 15 3 25  5 45
			int columnFirstChar = SIZE * r.nextInt(0, SIZE - word.length() + 1);// 5 00 4 01 3 02
			for(int i = 0 ; i < word.length() ; i++)
				board.replace(rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex]), rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex])+1, word.substring(i, i+1));
			
			if(r.nextBoolean()) board.reverse();
		} else {
			int rowFirstChar = r.nextInt(0, SIZE - word.length() + 1);//5 01 4 02
			int columnFirstChar = SIZE * r.nextInt(0, SIZE - word.length() + 1);// 5 00 4 01 3 02
			for(int i = 0 ; i < word.length() ; i++)
				board.replace(rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex]), rowFirstChar + columnFirstChar + (i * wordArrangeSize[sizeIndex])+1, word.substring(i, i+1));
			
			if(r.nextBoolean()) board.reverse();
		}
		
	}
	
	public void print() {
		String boardStr = board.toString();
		for(int i = 0 ; i < boardStr.length() ; i++) {  
			System.out.print(boardStr.charAt(i) + " ");
			if (i % SIZE == SIZE-1) System.out.println();
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CrossWord cw = new CrossWord();
		
		while(true) {
			System.out.print("단어>>");
			String input = scanner.nextLine();
			if(input.equals("그만")) 	break;
			if(input.length() > SIZE) {
				System.out.println(SIZE + "글자까지 입력할 수 있습니다.");
				continue;
			}
			cw.arrange(input);
			cw.print();
		}
		
		scanner.close();
	}

}
