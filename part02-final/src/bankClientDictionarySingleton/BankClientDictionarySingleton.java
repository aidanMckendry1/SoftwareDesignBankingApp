package bankClientDictionarySingleton;

import java.util.ArrayList;
import java.util.List;

import bankClient.BankClient;;

public class BankClientDictionarySingleton {
	private List<BankClient> bankClientList;
	private static BankClientDictionarySingleton singletonInstance = null;
	
	private BankClientDictionarySingleton() {
		bankClientList = new ArrayList<BankClient>();
	}
	public static BankClientDictionarySingleton getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new BankClientDictionarySingleton();
		}
		return singletonInstance;
	}
	public void addBankClient(BankClient bankClient) {
		String bcInUsername = bankClient.getUsername();
		String bcInPassword = bankClient.getPassword();
		if (searchBankClient(bcInUsername,bcInPassword) == -1) {
			bankClientList.add(bankClient);
		}
		
	}
	public void printBankClients() {
		for(BankClient bc : bankClientList) {
			System.out.println("***********************");
			System.out.println(bc.toString());
		}
	}
	public int searchBankClient(String username, String password) {
		String tempUsername;
		String tempPassword;
		for(int i=0; i<bankClientList.size(); i++) {
			tempUsername = bankClientList.get(i).getUsername();
			tempPassword = bankClientList.get(i).getPassword();
			if (username.contentEquals(tempUsername) && password.contentEquals(tempPassword)) {
				return i;
			}
		}
		return -1;
	}
	public int size() {
		return bankClientList.size();
	}
	public BankClient get(int position) {
		return bankClientList.get(position);
	}
	public void delete(int ID) {
		for (int i=0; i<bankClientList.size(); i++) {
			BankClient currentBC = bankClientList.get(i);
			if (currentBC.getID() == ID) {
				bankClientList.remove(i);
			}
		}
	}
	
}
