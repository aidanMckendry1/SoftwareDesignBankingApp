package bankClient;
import bankClientDictionarySingleton.BankClientDictionarySingleton;
import transactionTemplate.TransactionTemplate;

public class CreateBankAccount extends TransactionTemplate{

	private int accountNumber;
	private static int nextAccountNumber = 1;
	
	private String accountHolderUsername;
	private String accountHolderPassword;
	private BankClient bankClient;
	private String accountType;
	private BankClientDictionarySingleton bcDict;
	
	public CreateBankAccount() {
		bcDict = BankClientDictionarySingleton.getInstance();
		accountNumber=nextAccountNumber;
		nextAccountNumber++;
	}

	@Override
	protected void provideDetails() {
		provideLoginDetails();
		provideAccountType();
	}
	private void provideLoginDetails() {
		String[] bcLogin = BankClientMenu.readBankClientLogin();
		accountHolderUsername = bcLogin[0];
		accountHolderPassword = bcLogin[1];
	}
	private void provideAccountType() {
		String[] accountTypeOptions = {"Primary", "Savings", "Exit"};
		String choice = BankClientMenu.getUserChoice(accountTypeOptions, "Choose an Account Type");
		if (choice!="exit") {
			this.accountType = choice;
		}
	}

	@Override
	protected boolean checkDetails() {
		if (checkExit() != true) {
			checkNull();
			checkUsernameExists();
			if (error == "") {
				return true;
			}
		}
		return false;
	}
	private boolean checkExit() {
		if (accountType.contentEquals("Exit")) {
			return true;
		}
		return false;
	}
	private void checkNull() {
		if (accountHolderUsername == null) {
			error += "Username not given";
		}
		if (accountHolderPassword == null) {
			error += "Password not given";
		}
	}
	private void checkUsernameExists() {
		int index = bcDict.searchBankClient(accountHolderUsername, accountHolderPassword);
		if (index == -1) {
			error += "Account Details Invalid";
		}
		else {
			bankClient = bcDict.get(index);
		}
	}

	@Override
	protected void executeTransaction() {
		bankClient.addBankAccount(accountNumber+"", accountType);
		System.out.println("Bank account has been created");
	}
}
