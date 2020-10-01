package br.com.publicaproway.desafio.services;

import java.time.LocalDate;
import java.time.LocalTime;


public class TimeServices {

	
	public static String toFormatDDMMYYY(LocalDate localDate) {
		
		return localDate.getDayOfMonth()+"/"+localDate.getMonth()+"/"+localDate.getYear();
		
	}
	
	public static String toFormatHHMMSS(LocalTime localTime) {
		
		return localTime.getHour()+":"+localTime.getMinute()+":"+localTime.getSecond();
		
	}
	
	public static String toFormatDDMMYYYHHMMSS(LocalDate localDate, LocalTime localTime) {
		
		return toFormatDDMMYYY(localDate)+" "+toFormatHHMMSS(localTime);
		
	}
	
}
