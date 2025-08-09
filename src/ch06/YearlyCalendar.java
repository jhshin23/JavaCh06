package ch06;
import java.util.Calendar;
import java.util.Scanner;
//Page396, P07, 연간달력출력
public class YearlyCalendar {
	private int year;
	private Calendar theYear = Calendar.getInstance();
	public YearlyCalendar(int year) {
		this.year = year;
		theYear.set(Calendar.YEAR, year);
	}
	public void printAllMonth() {
		for(int i = 1 ; i <= 12 ; i++) {
			printMonth(i);
			System.out.println();
		}
	}
	public void printMonth(int month) {
		theYear.set(Calendar.MONTH, month-1);
		theYear.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(year + "년" + month + "월");
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		int i;
		for(i = 1 ; i < theYear.get(Calendar.DAY_OF_WEEK) ; i++) {
			System.out.print("\t");
		}
		for(; i < theYear.getActualMaximum(Calendar.DAY_OF_MONTH)+theYear.get(Calendar.DAY_OF_WEEK) ; i++) {
			System.out.print((i - (theYear.get(Calendar.DAY_OF_WEEK)-1)) + "\t");
			if(i%7 == 0) System.out.println();
		}
		
		System.out.println();
			
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int year = 0;
		while(true) {
			System.out.print("년도 입력(-1이면 종료)>>");
			String input = scanner.nextLine();
			try {
				year = Integer.parseInt(input);
			}
			catch(NumberFormatException e) {
				continue;
			}
			
			if(year == -1) break;
			
			new YearlyCalendar(year).printAllMonth();
			
		}
		scanner.close();
		
	}

}
