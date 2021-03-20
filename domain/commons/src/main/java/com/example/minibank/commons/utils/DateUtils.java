package com.example.minibank.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;

@Slf4j
public class DateUtils {

	public static BiFunction<Date, String, String> convertDateToString() {
		return (date, pattern) -> {

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);

			return formatter.format(date);
		};
	}

	public static BiFunction<String, String, Long> convertStringDateToLong() {
		return (stringDate, pattern) -> {

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);

			Date date;

			try {
				date = formatter.parse(stringDate);
			} catch (ParseException e) {

				log.info("{}", e.getMessage());
				return null;
			}

			return date.getTime();
		};
	}
}
