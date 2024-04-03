package employee;
import java.util.Scanner;

public class EmployeeMenu {

	public static int getIntChoice(String[] choices, String title) {
		System.out.println("******" + title + "******");
		for (int i=0; i<choices.length; i++) {
			System.out.println((i+1) + "\t" + choices[i]);
		}
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		return choice;
	}
}
