package ch06;
import java.util.Scanner;
//Page399, P11, 3개 숫자 일치 갬블, 멀티
class Player {
	private String name;
	private int[] luckyNum;
	public Player(String name) {
		this.name = name;
	}
	
	public boolean inputEnter(Scanner scanner) {
		scanner.nextLine();
		return true;
	}
	
	public void setLuckyNum(int[] ranNum) {
		luckyNum = ranNum;
	}
	
	public int[] getLuckyNum() {
		return luckyNum;
	}
	
	public String getName() {
		return name;
	}
	
	
}
public class GameblingGame {
	private static int size;
	private final int NUMBER_SIZE = 3;
	private Player[] gamer;
	
	public void setPlayer(Scanner scanner) {
		gamer = new Player[size];
		for(int i = 0 ; i < gamer.length ; i++) {
			System.out.print((i+1) + "번째 선수 이름>>");
			String name = scanner.nextLine();
			gamer[i] = new Player(name);
		}
	}
	
	public int[] setRanNum() {
		int ranNum[] = new int[NUMBER_SIZE]; 
		for(int i = 0 ; i < NUMBER_SIZE ; i++) {
			ranNum[i] = (int) (Math.random() * NUMBER_SIZE + 1);
			System.out.print("  " + ranNum[i]);
		}
		return ranNum; 
	}
	
	public boolean judge(int[] luckyNum) {
		for (int i = luckyNum.length-1 ; i > 0 ; i--) {
			if (luckyNum[i] != luckyNum[i-1]) {
				System.out.println("  아쉽군요!");
				return false;
			}
		}
		return true;
	}
	
	public void run(Scanner scanner) {
		setPlayer(scanner);
		while(true) {
			for(int i = 0 ; i < gamer.length ; i++) {
				System.out.print("[" + gamer[i].getName() + "]:<Enter>");
				if(gamer[i].inputEnter(scanner)) gamer[i].setLuckyNum(setRanNum());
				if(judge(gamer[i].getLuckyNum())) {
					System.out.println();
					System.out.println(gamer[i].getName() + "님이 이겼습니다!");
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				System.out.print("겜블링 게임에 참여할 선수 숫자>>");
				size = Integer.parseInt((scanner.nextLine().trim()));	
				if(size <=0) {
					System.out.println("1명 이상 입력해주세요");
					continue;
				}
				break;
			}
			catch (NumberFormatException e) {
				System.out.println("참여 수를 숫자로만 입력해주세요.");
				continue;
			}
			
		}
		GameblingGame gg = new GameblingGame();
		gg.run(scanner);
		
		scanner.close();
	}

}
