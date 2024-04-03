package admin;

import validators.Validators;

public class AdminNotification {
	private int accountNumber;
	private static int nextAccountNumber = 1;
	private String accountHolderUsername;
	private String accountType;
	private boolean malUserStatus = false;
	// either primary or savings
		
	public AdminNotification (String accountHolderUsername, String accountType) {
		this.accountNumber = nextAccountNumber;
		nextAccountNumber ++;
		this.accountHolderUsername = accountHolderUsername;
		if (!Validators.validateUsername(accountHolderUsername)) {
			malUserStatus = true;
		}
		this.accountType = accountType;
		
	}
	
	public boolean getMalUserStatus() {
		return malUserStatus;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getNextAccountNumber() {
		return nextAccountNumber;
	}

	public String getAccountHolderUsername() {
		return accountHolderUsername;
	}

	public String getAccountType() {
		return accountType;
	}

	@Override
	public String toString() {
		return "Account holders username : " + accountHolderUsername + "\n\tAccount type : " + accountType
				+ "\n\tNext available account number : " + getAccountNumber();
	}
	
}
