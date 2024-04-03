package validators;
import java.util.Date;

public class Validators {
	
	public static boolean validateUsername(String username) {		
		return !(username.charAt(0) == '$') ;
	}
	
	public static boolean validatePassword(String password) {
		return !(password.charAt(password.length() -1) == '.');
	}
	
	public static boolean validateDate(Date date) {
		Date today = new Date();
		
		return !(today.compareTo(date)>=0);
	}
}
