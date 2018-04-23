package com.jprens.controllers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {


	public static String decimalFormatter(float d) {
		DecimalFormat f = new DecimalFormat("#0.00");
		return f.format(d);
	}
	
	public static String dayMonthFormatter(int in) {
		DecimalFormat f = new DecimalFormat("00");
		return f.format(in);
	}

	public static String dateFormatter(Date date) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
		return dt.format(date);
	}

}
