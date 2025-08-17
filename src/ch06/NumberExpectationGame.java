package ch06;
import java.util.Scanner;
//Page400, P12, 정수 맞추기 게임, 멀티
class PlayerExpGame {
	private String name;
	private int luckyNum;
	private int result;
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
	
	public void setResult(int[] randomNum) {
		int luckyNum  = getLuckyNum();
		int compCnt = 0;
		for(int i = 0 ; i < randomNum.length ; i++) {
			if(luckyNum == randomNum[i]) compCnt++;
		}
		result = compCnt;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setIsWinner() {
		isWinner = true;
	}
	
	public boolean isWinner() {
		return isWinner;
	}
}
public class NumberExpectationGame {
	static final int MIN = 1; 
	static final int MAX = 10; 
	static final int RANDOM_NUM_SIZE = 15; 	
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
		int minCnt = RANDOM_NUM_SIZE + 1;
		for(PlayerExpGame p : gamer) {
			if(!p.isWinner()) minCnt = Math.min(minCnt, p.getResult());
		}
		for(int i = 0 ; i < gamer.length ; i++) {
			if(!(gamer[i].isWinner())) {
				if(minCnt == gamer[i].getResult()) {
				System.out.println("[" + gamer[i].getName() + "] 맞춘 개수: " + gamer[i].getResult());
				continue;
				}
				else {
					System.out.println("[" + gamer[i].getName() + "] 맞춘 개수: " + gamer[i].getResult());
					gamer[i].setIsWinner();
				}
			}
		}
		
		System.out.print("현재 패자들 : ");
		for(int i = 0 ; i < gamer.length ; i++) if(!gamer[i].isWinner()) System.out.print(gamer[i].getName() + " ");
		System.out.println();
		
		if(isGameOver()) {
			System.out.print("최종 패자는 ");
			for(PlayerExpGame p : gamer) {
				if(!(p.isWinner())) System.out.print(p.getName()+ " ");
			}
			System.out.println("입니다");
			return true;
		}
		else return false;
		
	}
	
	private boolean isGameOver() {
		for (int i = 0 ; i < gamer.length ; i++) {
			if(gamer[i].isWinner()) continue;
			else {
				for (int j = i+1 ; j < gamer.length ; j++) {
					if(!gamer[j].isWinner()) 
						if(gamer[i].getLuckyNum() != gamer[j].getLuckyNum()) return false;
				}
			}
		}
		return true; //만약 패자가 하나 초과면 그들이 같은 번호를 고른 경우이기 때문에 공동 패배(이기면 공동 승리)
	}
	
	
	public void run(Scanner scanner) {
		setPlayer(scanner);
		for(int i = 0 ; i < gamer.length ; i++) {
			gamer[i].setLuckyNum(scanner, MIN, MAX);
		}
		
		if(inputEnter(scanner)) makeRandomNum();
		
		for(int i = 0 ; i < gamer.length ; i++) gamer[i].setResult(randomNum);
		
		while (true) {
			if(judge()) return;
			if(inputEnter(scanner)) makeRandomNum();			
			for(int i = 0 ; i < gamer.length ; i++) gamer[i].setResult(randomNum);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		new NumberExpectationGame().run(scanner);
		
		scanner.close();
	}

}
