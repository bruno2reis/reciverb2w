package br.com.reciverb2w.reciverb2w.converteminuto;

import java.util.Calendar;

public class ConvertMinut {
	
	public int minutConv() {

		Calendar c = Calendar.getInstance();
		int minuto = c.get(Calendar.MINUTE);
		
		System.out.println(minuto);
	
    	return minuto;
	}

}


/*
 * long ms = minut; float segundos = ms / 1000; float minutos = segundos / 60;
 * 
 * DecimalFormat format = new DecimalFormat("0.00");
 * System.out.println(format.format(minutos));
 */