package com.semi.jdgr.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateTemplate {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	

	// 현재 날짜 구하기
	public static LocalDate findToday() {
		// 현재 날짜 구하기
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = dateFormat.format(c1.getTime());

		// 현재 날짜
		LocalDate today = LocalDate.parse(strToday, formatter);
		return today;
	}
	
	//디비에서 받은 날짜를 데이트까지만 짜름
	public static String changeDbDate(String DBdate) {
		// 디비 날짜 변경
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(DBdate, inputFormatter);

		// LocalDateTime을 다른 형식으로 포맷
		String formattedDate = dateTime.format(formatter);
		return formattedDate;
	}
	
	//디비에서 받은 날짜 타입 변경(String -> Localdate)
	public static LocalDate changeTypeDbDate(String DBdate) {
		String strDate = changeDbDate(DBdate);
		LocalDate date = LocalDate.parse(strDate, formatter);
		return date;
	}
}
