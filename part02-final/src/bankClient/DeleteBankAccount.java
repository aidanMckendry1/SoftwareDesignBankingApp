package bankClient;
import bankClientDictionarySingleton.BankClientDictionarySingleton;
import transactionTemplate.TransactionTemplate;

public class DeleteBankAccount extends TransactionTemplate{

	private BankClient bankClient;
	private String username;
	private String password;
	private String[] accountNumbers;
	private BankClientDictionarySingleton bcDict;
	
	public DeleteBankAccount() {
		bcDict = BankClientDictionarySingleton.getInstance();
	}
	
	@Override
	protected void provideDetails() {
		String[] loginDetails = BankClientMenu.readBankClientLogin();
		username = loginDetails[0];
		password = loginDetails[1];
	}

	@Override
	protected boolean checkDetails() {
		checkNull();
		checkLoginDetails();
		
		if (error.contentEquals("")) {
			return true;
		}
		return false;
	}
	private void checkNull() {
		if (username == null) {
			error += "Username not entered";
		}
		if (password == null) {
			error += "Password not entered";
		}
	}
	private void checkLoginDetails() {
		int index = bcDict.searchBankClient(username, password);
		if (index != -1) {
			bankClient = bcDict.get(index);
			accountNumbers = bankClient.getBankAccountNumbers();
		}
		else {
			error += "Login Details Invalid";
		}
	}
	
	@Override
	protected void executeTransaction() {
		String chosenAccNum = BankClientMenu.getUserChoice(accountNumbers, "Choose a Bank Account to Delete");
		int accIndex = getAccountIndex(chosenAccNum);
		bankClient.removeBankAccount(accIndex);
		if (accountNumbers.length<2) { //This list will still include the number before it was deleted, hence 2
			bcDict.delete(bankClient.getID());
		}
		
	}
	private int getAccountIndex(String accNumIn) {
		for (int i=0; i<accountNumbers.length; i++) {
			if (accountNumbers[i].contentEquals(accNumIn)) {
				return i;
			}
		}
		return -1;
	}
}
