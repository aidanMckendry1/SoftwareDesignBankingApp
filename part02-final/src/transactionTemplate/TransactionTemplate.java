package transactionTemplate;
import person.Person;

public abstract class TransactionTemplate {
	
	protected String error = "";
	protected Person person;
	
	public void run() {
		provideDetails();
		if (checkDetails() == true) {
			executeTransaction();
		}
		else {
			printErrorMessage();
		}
	}
	protected abstract void provideDetails();
	protected abstract boolean checkDetails();
	protected  void printErrorMessage() {
		if (error != "") {
			System.out.println("Error: " + error);
		}
	}
	protected abstract void executeTransaction();
}
