package study;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample4 {

	public static void main(String[] args) {
		Date date = new Date();

		Calendar currentDay = Calendar.getInstance();
		long cun = currentDay.getTime().getTime();

		System.out.println(cun);
	}
}