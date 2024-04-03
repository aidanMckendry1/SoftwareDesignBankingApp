package menu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
	
	protected static Scanner in = new Scanner(System.in);
	
	public static String getUserChoice(String[] options, String title) {
		int choice = -1;
		while(true) {
			//Creates a menu for the user, (i+1) provides a more user-friendly index
				System.out.println("<----" + "  " +title +"  " + "---->");
				for (int i=0; i<options.length; i++) {
					System.out.println((i+1) + ")\t" + options[i]);
				}
			
			choice=in.nextInt()-1; //-1 ensures proper array indexing
			//If choice is outside range, don't break while loop
			if (choice >options.length-1 || choice<0) {
				System.out.println("Please enter a valid choice");
			}
			else {
				break;
			}
				in.nextLine();
		}
		return options[choice];
	}
	
	public static void skipLine() {
		in.nextLine();
	}
	
	public static String userInput(String title) {
		System.out.print(title);
		String newData = in.nextLine();
		return newData;
	}
	public static Date userInputDate(String title) {
		System.out.print(title);

		String date = in.nextLine();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date testDate = null;
		try{
			testDate = df.parse(date);
		} catch (ParseException e){ 
			System.out.println("invalid format");
			return testDate;
			}
	 
		if (!df.format(testDate).equals(date)){
			System.out.println("invalid date!!");
			return testDate;

		} else {
	
			return testDate;
		}
	}
	public static void clearInput() {
		in.nextLine();
	}
}
