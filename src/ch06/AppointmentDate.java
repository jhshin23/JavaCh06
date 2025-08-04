package ch06;

import java.util.Calendar;
//Page393, T12
public class AppointmentDate {

	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		date.clear();
		date.set(Calendar.YEAR, 2020);
		date.set(Calendar.MONTH, 11);
		date.set(Calendar.DAY_OF_MONTH, 25);
		
		System.out.println(date.get(Calendar.YEAR)+ "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DAY_OF_MONTH) + "일");
	}

}
