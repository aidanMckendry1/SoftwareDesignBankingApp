package bankClient;

public class ViewBankDetails extends ViewDetails{

	private String[] bankAccountNumbers;
	private String choice;
	
	public ViewBankDetails(String username, String password) {
		super(username, password);
		bankAccountNumbers = bankClient.getBankAccountNumbers();
	}
	public void displayBankAccounts() {
		
		int index = getBankClientDetails();
		formatBankDetails(index);
	}
	private int getBankClientDetails() {
		choice = BankClientMenu.getUserChoice(bankAccountNumbers, "Choose Bank Account");
		int bankAccIndex;
		for(bankAccIndex=0; bankAccIndex<bankAccountNumbers.length; bankAccIndex++) {
			if (bankAccountNumbers[bankAccIndex] == choice) {
				return bankAccIndex;
			}
		}
		return -1;
	}
	private void formatBankDetails(int bankAccI) {
		String[] labels = {"Bank Account Number", "Bank Account Type", "Bank Account Balance"};
		
		String baNum = bankAccountNumbers[bankAccI];
		String baType = bankClient.getBankAccountTypes()[bankAccI];
		String baBalance = bankClient.getBankAccountBalances()[bankAccI];
		String[] data = {baNum, baType, baBalance};
		
		formatOutput(labels,data);
	}
}
