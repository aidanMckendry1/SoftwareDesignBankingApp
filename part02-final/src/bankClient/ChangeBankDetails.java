package bankClient;
import java.util.Date;

import bankClientDictionarySingleton.BankClientDictionarySingleton;
import transactionTemplate.TransactionTemplate;
import validators.Validators;

public class ChangeBankDetails extends TransactionTemplate {

	private String detailToChange;
	private String newData;
	String[] detailsOptions = {"Username", "Password", "Name", "Address"};
	BankClientDictionarySingleton bcDict;
	
	public ChangeBankDetails(BankClient bc) {
		person = bc;
		bcDict = BankClientDictionarySingleton.getInstance();
	}
	
	@Override
	protected void provideDetails() {
		
		detailToChange = BankClientMenu.getUserChoice(detailsOptions, "Choose Data to Change");
		BankClientMenu.clearInput();
		newData = BankClientMenu.userInput("Enter New " + detailToChange + ": ");
	}

	@Override
	protected boolean checkDetails() {
		if (detailToChange == null) {
			error += "Invalid values given";
			printErrorMessage();
			return false;
		}
		if (!(person instanceof BankClient)) {
			error += "Invalid user";
			return false;
		}
		if (detailToChange.equals(detailsOptions[0])) {
			if(!Validators.validateUsername(newData)) {
				error += "Malicious user detected";				
				return false;
			}
		}
		if (detailToChange.equals(detailsOptions[1])) {
			if(!Validators.validatePassword(newData)) {
				error += "Malicious user detected";
				return false;
			}
		}
		return true;
	}

	@Override
	protected void executeTransaction() {
		if(detailToChange == detailsOptions[0]) {
			bcDict.delete(((BankClient) person).getID());
			((BankClient) person).setUsername(newData);
			bcDict.addBankClient((BankClient)person); 
		}
		if(detailToChange == detailsOptions[1]) {
			bcDict.delete(((BankClient) person).getID());
			((BankClient) person).setPassword(newData);
			bcDict.addBankClient((BankClient)person); 
		}
		if(detailToChange == detailsOptions[2]) {
			bcDict.delete(((BankClient) person).getID());
			((BankClient) person).setName(newData);
			bcDict.addBankClient((BankClient)person); 
		}
		if(detailToChange == detailsOptions[3]) {
			bcDict.delete(((BankClient) person).getID());
			((BankClient) person).setAddress(newData);
			bcDict.addBankClient((BankClient)person);
		}
		
	}

	
}

	