package bankClient;
import bankClientDictionarySingleton.BankClientDictionarySingleton;
import transactionTemplate.TransactionTemplate;

public class ClientLogin extends TransactionTemplate {
	private String username;
	private String password;
	private BankClientDictionarySingleton bcDict;
	
	public ClientLogin() {
		bcDict = BankClientDictionarySingleton.getInstance();
	}

	@Override
	protected void provideDetails() {
		// TODO Auto-generated method stub
		String[] loginDetails = BankClientMenu.readBankClientLogin();
		username = loginDetails[0];
		password = loginDetails[1];
	}

	@Override
	protected boolean checkDetails() {
		checkNull();
		checkValidUser();
		if (error != "") {
			return false;
		}
		return true;
	}
	private void checkNull() {
		if (username==null) {
			error += "Missing username";
		}
		if (password==null) {
			error += "Missing password";
		}
	}
	private void checkValidUser() {
		if (bcDict.searchBankClient(username, password) == -1) {
			error += "User profile does not exist";
		}
		
	}

	@Override
	protected void executeTransaction() {
		// TODO Auto-generated method stub
		
	}
}
