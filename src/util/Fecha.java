package util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import exceptions.ServiceException;




public class Fecha {
	
	
	/**
	 * convierte un String de una fecha, a un date java.sql.Date
	 * 
	 * @param fecha  String con formato de la mascara
	 * @param mascara String constante separadores validos (solo / � solo -)
	 *                    "yyyy/MM/dd"
	 *                    "dd/MM/yyyy"  
	 *                    "dd/MM/yyyy"
	 *                    "yyyy-MM-dd" .... ect. � con tiempos
	 *                    "dd/MM/yyyy/hh/mm/ss" ... ect
	 * @return   java.sql.Date.
	 * @throws ParseException
	 */
	public static java.sql.Date convertirADate(String fecha,String mascara) throws ParseException {
		if (fecha == null||fecha.trim().length()==0 )
			return null;
		else{
			SimpleDateFormat formato = new SimpleDateFormat(mascara);
			formato.setLenient(false);
			long milis;
			milis = formato.parse(fecha).getTime();	
			return new java.sql.Date(milis);
		}	
	}
	
	/**
	 * 
	 * Convierte un String de fecha con una mascara ,a Timestamp (fecha con milisegundos)
	 * @param fecha
	 * @param mascara
	 * @return TimeStamp 
	 */
	
	public static Timestamp convertirATimestamp(String fecha, String mascara) throws ParseException  {	
		if (fecha ==null||fecha.compareTo("")==0 )
			return null;
		else {
			SimpleDateFormat formato = new SimpleDateFormat(mascara);
			formato.setLenient(false);
			long milis;
			milis = formato.parse(fecha).getTime();
			return new Timestamp(milis);
		}
	}

/**
 * convierte un  java.sql.date a un String con una mascara
 * @param fecha  java.sql.Date
 * @param mascara String constante separadores validos 
 *               cualquier combinacion de  /,-,: blancos 
 *               
 * @return 
 */
	public static String convertirAString(java.sql.Date fecha,String mascara) {
		if (fecha==null )
			return null;
	    else{
	        SimpleDateFormat formato = new SimpleDateFormat(mascara);
	        formato.setLenient(false);
	        return formato.format(fecha);
	    }
	}
	
	public static String convertirAString(java.sql.Timestamp  fecha,String mascara) {
		if (fecha==null )
			return null;
		else{
			SimpleDateFormat formato = new SimpleDateFormat(mascara);
			formato.setLenient(false);
			return formato.format(fecha);
		}	
	}
		
	// fecha y tiempo pero no milisegindos
	public static java.sql.Date fechaActualDate()  {
		java.sql.Date CurrentDate = new java.sql.Date (Calendar.getInstance().getTime().getTime());
		return CurrentDate;
	}

	// fecha tiempo  y milisegundos.
	public static java.sql.Timestamp fechaActual()  {
		 Timestamp CurrentTimestamp = new java.sql.Timestamp (Calendar.getInstance (). getTime (). getTime ());
		return CurrentTimestamp;
	}
	
	/**
	 * 
	 * @param fecha1 
	 * @param fecha2
	 * @return 1,-1,0 segun fecha1 sea mayor,menor o igual que fecha2
	 * @throws ServiceException 
	 */
	public static int compararFechas(java.sql.Date fecha1,
                                     java.sql.Date fecha2) throws ServiceException{
        if(fecha1!=null && fecha2!=null){
		long msinicial = fecha1.getTime();
		long msfin = fecha2.getTime();
		
		if(msinicial>msfin )  
		return 1;  //Fecha1 mayor que fecha2
		else if(msinicial<msfin ) 
		return -1; //Fecha1 menor que fecha2
		else
		return 0; //Fecha1 igual que fecha2
        }
        else
        	throw new ServiceException("se estan comparando fechas nulas");
	}      
 public static int compararFechas(java.sql.Timestamp fecha1,
            java.sql.Timestamp fecha2) throws ServiceException{
	 if(fecha1!=null && fecha2!=null){
			long msinicial = fecha1.getTime();
			long msfin = fecha2.getTime();
			
			if(msinicial>msfin )  
			return 1;  //Fecha1 mayor que fecha2
			else if(msinicial<msfin ) 
			return -1; //Fecha1 menor que fecha2
			else
			return 0; //Fecha1 igual que fecha2
	 }else throw new ServiceException("se estan comparando fechas nulas");
}
	/**
	 * Devuelve una fecha resultado de la suma de otra fecha mas 'dias'
	 * @param fechaOriginal 
	 * @param dias
	 * @return fechaResultado
	 */
	public static java.sql.Date sumarDiasFecha(java.sql.Date fechaoriginal, int dias) {
		if(fechaoriginal!=null){
		long msOriginal = fechaoriginal.getTime();
		long msDias = TimeUnit.MILLISECONDS.convert(dias, TimeUnit.DAYS);
		java.sql.Date fechaResultado = new java.sql.Date(msOriginal + msDias);
		return fechaResultado;
		}else
		 return null;	
	}
	public static java.sql.Timestamp sumarDiasFecha(java.sql.Timestamp fechaoriginal, int dias) {
		if(fechaoriginal!=null){
		long msOriginal = fechaoriginal.getTime();
		long msDias = TimeUnit.MILLISECONDS.convert(dias, TimeUnit.DAYS);
		java.sql.Timestamp fechaResultado = new java.sql.Timestamp(msOriginal + msDias);
		return fechaResultado;
		}else
			 return null;
	}

	/**
	 * Devuelve una fecha resultado de la suma de otra fecha mas 'minutos'
	 * @param fechaOriginal 
	 * @param minutos
	 * @return fechaResultado
	 */
	public static java.sql.Date sumarMesesFecha(java.sql.Date fechaoriginal, int minutos) {
		if(fechaoriginal!=null){
		long msOriginal = fechaoriginal.getTime();
		long msMinutos = TimeUnit.MILLISECONDS.convert(minutos, TimeUnit.MINUTES);
		java.sql.Date fechaResultado = new java.sql.Date(msOriginal + msMinutos);
		return fechaResultado;
		}else
		 return null;	
	}
	
	public static java.sql.Timestamp sumarMesesFecha(java.sql.Timestamp fechaoriginal, int minutos) {
		if(fechaoriginal!=null){
		long msOriginal = fechaoriginal.getTime();
		long msMinutos = TimeUnit.MILLISECONDS.convert(minutos, TimeUnit.MINUTES);
		java.sql.Timestamp fechaResultado = new java.sql.Timestamp(msOriginal + msMinutos);
		return fechaResultado;
		}else
		 return null;	
	}
	
	
	/**
	 * Name of the month being January 1 and December 12
	 * @throws ServiceException 
	 * @return nameOfMonth
	 */
	public static String nameOfMonth(int num_month) throws ServiceException{
		switch(num_month){
			case 1:return "January";
			case 2:return "February";
			case 3:return "March";
			case 4:return "April";
			case 5:return "May";
			case 6:return "June";
			case 7:return "July";
			case 8:return "August";
			case 9:return "September";
			case 10:return "October";
			case 11:return "November";
			case 12:return "December";
			default:throw new ServiceException("The num of month is incorrect");
		}
	}

}// fin de la clase
