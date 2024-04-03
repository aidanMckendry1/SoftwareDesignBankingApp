package bankClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bankClientDictionarySingleton.BankClientDictionarySingleton;
import employee.Employee;
import employee.EmployeeDictionarySingleton;
import menu.Menu;
import transactionTemplate.TransactionTemplate;

public class BankClientUI{
	static BankClient bankClient;
	static String username;
	static String password;
	static boolean login = false;
	static BankClientDictionarySingleton bcDict = BankClientDictionarySingleton.getInstance();
	static EmployeeDictionarySingleton eDict = EmployeeDictionarySingleton.getInstance();
	
	public static void main(String[] args) {
		addExampleBC();
		addExampleEmployee();
		while(true) {
			//Repeatedly asks user for login information until correct data given
			//OR to register an Account
			String[] loginOptions = {"Create New Account", "Login to an Existing Account", "Exit"};
			String loginMenuChoice = BankClientMenu.getUserChoice(loginOptions, "Login/Register Menu");
			
			//Create New Account
			if (loginMenuChoice == loginOptions[0]) {
				while(true) {
					String[] newAccountOptions = {"Enter Details", "Exit"};
					String newAccountChoice = BankClientMenu.getUserChoice(newAccountOptions, "Create New Account");
					if (newAccountChoice == newAccountOptions[0]) {
						Menu.skipLine();
						TransactionTemplate newBC = new CreateBankClient();
						newBC.run();
					}
					else {
						break;
					}
				}
			}
			//Login to an account
			else if (loginMenuChoice == loginOptions[1]){
				Menu.clearInput();
				String[] userPass = BankClientMenu.readBankClientLogin();
				int index = bcDict.searchBankClient(userPass[0], userPass[1]);
				if (index != -1) {
					username=userPass[0];				
					password=userPass[1];
					login=true;
					bankClient=bcDict.get(index);
					if (bankClient.getMalUserStatus()) {
						login = false;
						System.out.println("Malicious user detected, login refused");
						break;
					}
					break;
				}
				else {
					System.out.println("Invalid Details");
				}
			}
			else {
				login=false;
				break;
			}
			
		}
		//Allows user to indefinitely browse menu
		while(login==true) {
			String[] mainMenuOptions = {"View Profile Details", "Change Bank Details", "Create Bank Account", "Delete Bank Account", "Transfer Money", "Book an Appointment"};
			String choice = BankClientMenu.getUserChoice(mainMenuOptions, "Main Menu");
			
			//View Details
			if (choice == mainMenuOptions[0]) {
				String[] viewDetailsOptions = {"View Bank Details", "View Personal Data"};
				String dataViewChoice = BankClientMenu.getUserChoice(viewDetailsOptions, "Choose Data to view");
				
				//View Bank Details
				if (dataViewChoice == viewDetailsOptions[0]) {
					ViewBankDetails viewBankDetails = new ViewBankDetails(username, password);
					viewBankDetails.displayBankAccounts();
				}
				//View Personal Details
				else {
					ViewPersonalDetails viewPersDetails = new ViewPersonalDetails(username,password);
					viewPersDetails.viewPersonalDetails();
				}
				
			}
			//Change Bank Details
			else if(choice == mainMenuOptions[1]) {
				TransactionTemplate changeBankDetails = new ChangeBankDetails(bankClient);
				changeBankDetails.run();
				refreshLogin();
			}
			//Create Bank Account
			else if (choice == mainMenuOptions[2]) {
				Menu.clearInput();
				TransactionTemplate newBankAccount = new CreateBankAccount();
				newBankAccount.run();
			}
			//Delete Bank Account
			else if (choice == mainMenuOptions[3]){
				Menu.clearInput();
				TransactionTemplate deleteBankAccount = new DeleteBankAccount();
				deleteBankAccount.run();
				
			}
			//Transfer Money
			else if(choice == mainMenuOptions[4]) {
				TransactionTemplate newTransfer = new BankTransfer();
				newTransfer.run();
			}
			//Book an appointment
			else if (choice == mainMenuOptions[5]) {
				TransactionTemplate newAppointment = new BookAnAppointment(bankClient);
				newAppointment.run();
			}
		}
	}
	private static boolean refreshLogin() {
		System.out.println("<-- Please enter your login details -->");
		String userTemp = Menu.userInput("Enter your username: ");
		String passTemp = Menu.userInput("Enter your password: ");
		
		int index = bcDict.searchBankClient(userTemp, passTemp);
		if (index != -1) {
			BankClient bc = bcDict.get(index);
			username = bc.getUsername();
			password = bc.getPassword();
			return true;
		}
		return false;
	}
	
	private static void addExampleBC() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try{
			Date egDoB = dateFormat.parse("27/09/2030"); //INVALID DATE OF BIRTH, MALICIOUS USER
			BankClient egBC1 = new BankClient("user1", "pass1", "eg1", "address", egDoB);
			egBC1.addBankAccount("10", "Savings");
			egBC1.setBalance(100, 0);
			egBC1.addBankAccount("100", "Primary");
			egBC1.setBalance(190, 1);
			bcDict.addBankClient(egBC1);
			
			Date egDoB2 = dateFormat.parse("27/03/2010");
			BankClient egBC2 = new BankClient("user2", "pass2", "eg2", "address", egDoB2);
			egBC2.addBankAccount("10", "Savings");
			egBC2.setBalance(1912, 0);
			egBC2.addBankAccount("100", "Primary");
			egBC2.setBalance(1012, 1);
			egBC2.addBankAccount("101", "Savings");
			egBC2.setBalance(161, 2);
			bcDict.addBankClient(egBC2);
		}catch (ParseException e) {
			 e.printStackTrace();
		}
	}
	private static void addExampleEmployee() {
		//CREATE EMPLOYEE SINGLETON
		eDict.addEmployee(new Employee("Mary"));
	}
}
