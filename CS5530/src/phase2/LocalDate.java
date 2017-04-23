package phase2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocalDate {
	LocalDate(){}
	
	public static String getSQLDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();   
		if(date.getDate() > 1){
			date.setDate(date.getDate()-1);
		}
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");          
		String inActiveDate = null;
		
		inActiveDate = format1.format(date);

		return inActiveDate;
	}
}
