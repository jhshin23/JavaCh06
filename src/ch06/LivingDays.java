package ch06;
import java.util.Calendar;
import java.util.Scanner;

class CalcDay {
	private final int YEAR = 0, MONTH = 1, DAY = 2;
	private Calendar now = Calendar.getInstance();
	private Calendar birth = Calendar.getInstance();
	public CalcDay() {
		System.out.println("오늘은 " + now.get(Calendar.YEAR) + "년 " + (now.get(Calendar.MONTH)+1) + "월 " + now.get(Calendar.DAY_OF_MONTH) + "일");
	}
	
	public boolean inputBirthday(String eightBirthDate) {
		String ymd[] = eightBirthDate.split(" ");
		if (ymd.length != DAY+1) {
			System.out.println("년 월 일 구분하기 위한 빈칸을 한 개씩 입력해주세요");
			return false;
		}

		int birthYMD[] = new int[ymd.length];
		for(int i = 0 ; i < ymd.length ; i++) {
			try {
				birthYMD[i] = Integer.parseInt(ymd[i].trim());
			}
			catch (NumberFormatException e) {
				System.out.println("년 월 일 숫자와 빈칸만 입력해주세요.");
				return false;
			}
		}
		birth.set(Calendar.YEAR, birthYMD[YEAR]);
		
		if(birthYMD[MONTH] < 1 || birthYMD[MONTH] > 12) {
			System.out.println("월 입력이 잘못되었습니다.");
			return false;
		}
		birth.set(Calendar.MONTH, birthYMD[MONTH]-1);
		
		if(birthYMD[DAY] < birth.getActualMinimum(Calendar.DAY_OF_MONTH) || birthYMD[DAY] > birth.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			System.out.println("일 입력이 잘못되었습니다.");
			return false;
		}
		birth.set(Calendar.DAY_OF_MONTH, birthYMD[DAY]);
		
		return true;
	}
	
	private boolean isBorn() {
		if(birth.get(Calendar.YEAR) < now.get(Calendar.YEAR)) 
			return true;
		else if (birth.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
			if (birth.get(Calendar.MONTH) < now.get(Calendar.MONTH)) 
				return true;
			else if (birth.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
				if (birth.get(Calendar.DAY_OF_MONTH) <= now.get(Calendar.DAY_OF_MONTH)) 
					return true;
				else return false;
			}
			else return false;
		}
		else return false;
	}
 	
	private int calcDays() {
		if(isBorn()) {
			int bornYearDays = 0, wholeYearDays = 0, nowYearDays = 0;
			Calendar birthYearLastDay = Calendar.getInstance();
			birthYearLastDay.set(Calendar.YEAR, birth.get(Calendar.YEAR));
			birthYearLastDay.set(Calendar.MONTH, Calendar.DECEMBER);
			birthYearLastDay.set(Calendar.DAY_OF_MONTH, birthYearLastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			if(birth.get(Calendar.YEAR) < now.get(Calendar.YEAR)) {
				bornYearDays = birthYearLastDay.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR);
			
				for (int i = birth.get(Calendar.YEAR) + 1 ; i < now.get(Calendar.YEAR) ; i++) {
					Calendar middleYear = Calendar.getInstance();
					middleYear.set(Calendar.YEAR, i);
					middleYear.set(Calendar.MONTH, Calendar.DECEMBER);
					middleYear.set(Calendar.DAY_OF_MONTH, middleYear.getActualMaximum(Calendar.DAY_OF_MONTH));		
					wholeYearDays += middleYear.get(Calendar.DAY_OF_YEAR);
				}
			
				nowYearDays = now.get(Calendar.DAY_OF_YEAR);
			}
			else bornYearDays += now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR);
			
			return bornYearDays + wholeYearDays + nowYearDays;
		}
		else {
			int bornYearDays = 0, wholeYearDays = 0, nowYearDays = 0;

			Calendar nowLastDay = Calendar.getInstance();
			nowLastDay.set(Calendar.YEAR, now.get(Calendar.YEAR));
			nowLastDay.set(Calendar.MONTH, Calendar.DECEMBER);
			nowLastDay.set(Calendar.DAY_OF_MONTH, nowLastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			if(birth.get(Calendar.YEAR) > now.get(Calendar.YEAR)) {
				nowYearDays = nowLastDay.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
			
				for (int i = now.get(Calendar.YEAR) + 1 ; i < birth.get(Calendar.YEAR) ; i++) {
					Calendar middleYear = Calendar.getInstance();
					middleYear.set(Calendar.YEAR, i);
					middleYear.set(Calendar.MONTH, Calendar.DECEMBER);
					middleYear.set(Calendar.DAY_OF_MONTH, middleYear.getActualMaximum(Calendar.DAY_OF_MONTH));		
					wholeYearDays += middleYear.get(Calendar.DAY_OF_YEAR);
				}
				bornYearDays = birth.get(Calendar.DAY_OF_YEAR);
			}
			else bornYearDays = birth.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
			
			return bornYearDays + wholeYearDays + nowYearDays;
		}
	}
	
	public void print() {
		if(isBorn()) 
			System.out.println("오늘까지 " + calcDays() + "일 살아왔습니다.");
		else 
			System.out.println(calcDays() + "일 더 살아야 생일이 됩니다.");
	}
	
}
public class LivingDays {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		CalcDay today = new CalcDay();
		
		while(true) {
			System.out.print("생일 입력(년 월 일)>>");
			String input = scanner.nextLine();
			if (input.equals("그만")) break;
			
			if(!today.inputBirthday(input)) continue;
			today.print();
		}
		
		scanner.close();
	}

}
