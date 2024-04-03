package bankClient;
import bankClientDictionarySingleton.BankClientDictionarySingleton;

public class ViewDetails {
	
	protected BankClient bankClient;

	public ViewDetails(String username, String password) {
		BankClientDictionarySingleton bcDict = BankClientDictionarySingleton.getInstance();
		int bankClientIndex = bcDict.searchBankClient(username, password);
		bankClient = bcDict.get(bankClientIndex);
	}
	protected void formatOutput(String[] labels, String[] data) {
		System.out.println("<--- Personal Details --->");
		for(int i=0; i<labels.length; i++) {
			System.out.println(labels[i] + ":\t" + data[i]);
		}
	}
}
