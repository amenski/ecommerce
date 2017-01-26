package com.spring.project.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MDate {

	private Calendar cl;
	private String date_st;

	public MDate() {
		this.cl = new GregorianCalendar();
	}

	public MDate(int year, int month, int date) {
		this.cl = new GregorianCalendar(year, month, date);

	}

	public MDate(String dateString) {
		date_st = dateString;
		int[] dateArray = this.splitDate(dateString);
		this.cl = new GregorianCalendar(dateArray[0], dateArray[1], dateArray[2]);

	}

	public int[] splitDate(String dateString) {
		String[] dateSplit = dateString.split("/");
		if (dateSplit.length == 1)
			dateSplit = dateString.split("-");
		int[] dateArray = { Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
				Integer.parseInt(dateSplit[2]) };
		return dateArray;
	}

	public String getDateOfWeek() {
		String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		return daysOfWeek[this.cl.get(Calendar.DAY_OF_WEEK)];
	}

	public long getMillis() {
		return this.cl.getTimeInMillis();
	}

	public Date getDateObject() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = (Date)formatter.parse(date_st);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	public String getDateFormat(int type) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
		switch (type) {
		case 1:
			ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
			break;
		case 2:
			ft = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a");
			break;

		}
		return ft.format(new Date());
	}

	public long dateDifference(MDate c2) {
		long seconds = (c2.getMillis() - this.getMillis()) / 1000;
		long days = seconds / 60 * 60 * 24;
		if (days < 0)
			days *= -1;
		return days;
	}

	// TODO : addDate
}
