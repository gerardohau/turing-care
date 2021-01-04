package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

@Service
public class utilsService {

	public Date convertStringToDate(String date) {
		StringTokenizer tokens = new StringTokenizer(date, "/");

		String diaStr = tokens.nextToken();
		String mesStr = tokens.nextToken();
		String anioStr = tokens.nextToken();

		int dia = Integer.parseInt(diaStr);
		int mes = Integer.parseInt(mesStr) - 1;
		int anio = Integer.parseInt(anioStr);

		Calendar cal = Calendar.getInstance(new Locale("es", "MX"));
		cal.set(anio, mes, dia);

		return cal.getTime();
	}
}
