package bankClient;

import bankClientDictionarySingleton.BankClientDictionarySingleton;
import transactionTemplate.TransactionTemplate;

public class BankTransfer extends TransactionTemplate{
	private BankClient bankClient;
	private BankClientDictionarySingleton bcDict;
	
	private String username;
	private String password;

	private int sourceIndex = -1;
	private int destinationIndex = -1;
	
	private String sourceAccount;
	private String destinationAccount;
	private double transferAmount = -1;
	
	public BankTransfer () {
		bcDict = BankClientDictionarySingleton.getInstance();
	}
	
	@Override
	protected void provideDetails() {
		BankClientMenu.clearInput();
		username = BankClientMenu.userInput("Enter username: ");
		password = BankClientMenu.userInput("Enter password: ");
		sourceAccount = BankClientMenu.userInput("Source account");
		destinationAccount = BankClientMenu.userInput("Destination account");
		String tempTransferAmount = BankClientMenu.userInput("Transfer amount");
		try {
			transferAmount = Double.parseDouble(tempTransferAmount);
		} catch(Error e) {
			transferAmount = -1; //-1 Signifies error
		}
	}


	@Override
	protected boolean checkDetails() {
		checkNull();
		checkValidBC();
		checkValidAccounts();
		checkSourceBalance();
		if (error != "") {
			return false;
		}
		return true;
	}
	private void checkNull() {
		if (sourceAccount == null) {
			error += "No source account selected";
		}
		if (destinationAccount == null) {
			error += "No destination account selected";
		}
		if (transferAmount == -1) {
			error += "No transfer amount given";
		}
		if (username == null) {
			error += "No username given";
		}
		if (password == null) {
			error += "No password given";
		}
	}
	private void checkValidBC() {
		int index = bcDict.searchBankClient(username, password);
		if (index != -1) {
			bankClient = bcDict.get(index);
		} else {
			error += "Invalid username/password";
			bankClient = null;
		}
	}
	private void checkValidAccounts() {
		if (bankClient != null) {
			String[] baNums = bankClient.getBankAccountNumbers();
			for(int i=0; i<baNums.length; i++) {
				if (baNums[i].contentEquals(sourceAccount)) {
					sourceIndex = i;
				}
				if (baNums[i].contentEquals(destinationAccount)) {
					destinationIndex = i;
				}
			}
		}
		if (sourceIndex == -1 || destinationIndex == -1) {
			error += "Invalid account entered";
		}
		
		
	}
	private void checkSourceBalance() {
		double accBalanceD;
		if (sourceIndex>-1) {
			try {
				String accBalance = bankClient.getBankAccountBalances()[sourceIndex];
				
				accBalanceD = Double.parseDouble(accBalance);
				
			}catch(Error e) {
				accBalanceD = -1;
			}
			if (accBalanceD<transferAmount) {
				error += "Insufficient funds";
			}
		}
		
	}


	@Override
	protected void executeTransaction() {
		bankClient.subMoneyFrom(transferAmount, sourceAccount);
		bankClient.addMoneyTo(transferAmount, destinationAccount);
	}

}
