package ch06;
import java.util.Scanner;
//Page400, P12, 정수 맞추기 게임, 멀티
class PlayerExpGame {
	private String name;
	private int luckyNum;
	private int resulCnt;
	private boolean isWinner = false;
	public PlayerExpGame(String name) {
		this.name = name;
	}
	
	public void setLuckyNum(Scanner scanner, int min, int max) {
		System.out.print("["+ getName() + "] 정수 선택(" + min + "~" + max + ")>>");
		while(true) {
			try {
				luckyNum = Integer.parseInt(scanner.nextLine().trim());
				if(luckyNum < min || luckyNum > max) {
					System.out.print("정수 선택 범위: " + min + "부터" + max + "까지>>");
					continue;
				}
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
		}
	}
	
	public int getLuckyNum() {
		return luckyNum;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCnt(int[] randomNum) {
		int LuckyNum  = getLuckyNum();
		int compCnt = 0;
		for(int i = 0 ; i < randomNum.length ; i++) {
			if(LuckyNum == randomNum[i]) compCnt++;
		}
		resulCnt = compCnt;
	}
	
	public int getCnt() {
		return resulCnt;
	}
	
	public void setIsWinner() {
		isWinner = true;
	}
	
	public boolean getIsWinner() {
		return isWinner;
	}
}
public class NumberExpectationGame {
	static final int MIN = 1; 
	static final int MAX = 10; 
	private int RANDOM_NUM_SIZE = 15; 	
	private int [] randomNum = new int [RANDOM_NUM_SIZE];
	private PlayerExpGame[] gamer;


	public void setPlayer(Scanner scanner) {
		System.out.print("게임에 참여할 선수들 이름>>");
		String nameInput;
		String[] nameArray;
		while(true) {
			nameInput = scanner.nextLine();
			nameArray = nameInput.split(" ");
			if(nameArray.length <= 1) System.out.println("선수는 두 명 이상이어야 합니다.");
			else break;
		}
		int size = nameArray.length;
		gamer = new PlayerExpGame[size];

		for(int i = 0 ; i < gamer.length ; i++) {
			gamer[i] = new PlayerExpGame(nameArray[i]);
		}
	}
	
	public boolean inputEnter(Scanner scanner) {
		System.out.print("Enter키 입력>>");
		scanner.nextLine();
		return true;
	}
	
	public void makeRandomNum() {
		for (int i = 0 ; i < randomNum.length ; i++) {
			randomNum[i] = (int) (Math.random() * (MAX - MIN + 1) + MIN);
			System.out.print(randomNum[i] + " ");
		}
		System.out.println();
	}
	
	public boolean judge() {
		int minCnt = gamer[0].getCnt();
		int leftOneIndex = 0, leftGamer = 0;
		for(PlayerExpGame p : gamer) {
			if(!p.getIsWinner()) minCnt = Math.min(minCnt, p.getCnt());
		}
		for(int i = 0 ; i < gamer.length ; i++) {
			if(!gamer[i].getIsWinner()) {
				if(minCnt == gamer[i].getCnt()) {
				System.out.println("[" + gamer[i].getName() + "] 맞춘 개수: " + gamer[i].getCnt());
				continue;
				}
				else {
					System.out.println("[" + gamer[i].getName() + "] 맞춘 개수: " + gamer[i].getCnt());
					gamer[i].setIsWinner();
				}
			}
		}
		
		System.out.print("현재 패자들 : ");
		for(int i = 0 ; i < gamer.length ; i++) {
			if(!gamer[i].getIsWinner()) {
				System.out.print(gamer[i].getName() + " ");
				leftOneIndex = i;
				leftGamer++;
			}
		}
		System.out.println();
		
		if(leftGamer == 1) {
			System.out.println("최종 패자는" + gamer[leftOneIndex].getName() + "입니다");
			return true;
		}
		else return false;
		
	}
	
	public void run(Scanner scanner) {
		setPlayer(scanner);
		for(int i = 0 ; i < gamer.length ; i++) {
			gamer[i].setLuckyNum(scanner, MIN, MAX);
		}
		
		if(inputEnter(scanner)) makeRandomNum();
		
		for(int i = 0 ; i < gamer.length ; i++) gamer[i].setCnt(randomNum);
		
		while (true) {
			if(judge()) return;
			if(inputEnter(scanner)) makeRandomNum();			
			for(int i = 0 ; i < gamer.length ; i++) gamer[i].setCnt(randomNum);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		new NumberExpectationGame().run(scanner);
		
		scanner.close();
	}

}
