package bankClient;
import java.util.Date;

import bankClientDictionarySingleton.BankClientDictionarySingleton;
import menu.Menu;
import transactionTemplate.TransactionTemplate;
import validators.Validators;

public class CreateBankClient extends TransactionTemplate{
	private String username;
	private String password;
	private String name;
	private Date dob;
	private String address;
	private BankClientDictionarySingleton bcDict;
	private String[] extraAccountOptions = {"NO extra account","Extra account"};
	
	public CreateBankClient() {
		bcDict = BankClientDictionarySingleton.getInstance();
	}
	@Override
	protected void provideDetails() {
		// TODO Auto-generated method stub
		username = BankClientMenu.userInput("Please enter a new username");
		password = BankClientMenu.userInput("Please enter a new password");
		name = BankClientMenu.userInput("Please enter a new name");
		dob = Menu.userInputDate("Please enter your date of birth (dd/MM/yyyy)") ;
				
				
		//BankClientMenu.userInput("Please enter a new date of birth");
		address = BankClientMenu.userInput("Please enter a new address");
	}
	@Override
	protected boolean checkDetails() {
		checkNull();
		checkUsernamePasswordExist();
		checkMalUserStatus();
		if (error == "") {
			return true;
		}
		return false;
	}
	private void checkNull() {
		if (username == null) {
			error += "No username entered";
		}
		if (password == null) {
			error += "No password entered";
		}
		if (name == null) {
			error += "No name entered";
		}
		if (dob == null) {
			error += "No date of birth entered";
		}
		if (address == null) {
			error += "No address entered";
		}
	}
	private void checkUsernamePasswordExist() {
		int index = bcDict.searchBankClient(username, password);
		if (index != -1) {
			error += "Username Exists";
		}
	}
	private void checkMalUserStatus() {
		if (!Validators.validateUsername(username)) {
			error += "Malicious user detected";

		}
		else if(!Validators.validatePassword(password)) {
			error += "Malicious pass detected";
		}
		else if(Validators.validateDate(dob)) {
			error += "Malicious date detected";
		}
	}
	@Override
	protected void executeTransaction() {
		BankClient newBC = new BankClient(username, password, name, address, dob);
		bcDict.addBankClient(newBC);
		System.out.println("In order to finalise your Bank Client creation, you must create a Bank Account");
		CreateBankAccount createBA = new CreateBankAccount();
		createBA.run();
		while(true) {
			String extraAccChoice = BankClientMenu.getUserChoice(extraAccountOptions, "Would you like another account?");
			if (extraAccChoice == extraAccountOptions[0]) {
				break;
			}
			else {
				Menu.clearInput();
				TransactionTemplate createAnotherBankAccount = new CreateBankAccount();
				createAnotherBankAccount.run();
			}
		}
	
	}
	
	
}
