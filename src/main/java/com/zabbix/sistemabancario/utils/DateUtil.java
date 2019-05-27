package com.zabbix.sistemabancario.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author janaina militão
 */

public class DateUtil {

	public static Date retornarDataInicio(int mes, int ano) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mes);
		c.set(Calendar.YEAR, ano);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();

	}

	public static Date retornarDataFim(int mes, int ano) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mes);
		c.set(Calendar.YEAR, ano);
		c.set(Calendar.DAY_OF_MONTH, retornarUltimoDiaDoMes(mes));
		return c.getTime();
	}

	public static int retornarUltimoDiaDoMes(int mes) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mes); 
		int ultimoDia = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return ultimoDia;
	}

}
