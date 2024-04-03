package bankClient;

import menu.Menu;

public class BankClientMenu extends Menu{
	
	public static String[] readBankClientLogin() {
		String username = userInput("Please enter your username: ");
		String password = userInput("Please enter your password: ");
		String[] userPass = {username,password};
		return userPass;
	}
	public static String[] readBankClientRegister() {
		in.nextLine();
		String username = userInput("Please enter a valid username: ");
		String password = userInput("Please enter a valid password: ");
		String name = userInput("Please enter your name: ");
		String address = userInput("Please enter your address: ");
		String dob = userInput("Please enter your Date of Birth (DD/MM/YYYY): ");
		String[] details = {username,password,name,address,dob};
		return details;
	}
	
}
