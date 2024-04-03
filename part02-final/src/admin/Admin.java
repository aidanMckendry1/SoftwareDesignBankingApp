package admin;
import java.util.ArrayList;
import java.util.List;

import person.Person;

public class Admin extends Person{
	private List<String> bankAccountsToVerify;
	private List<String> bankClientsToVerify;
	
	public Admin(String name) {
		super(name);
		bankAccountsToVerify = new ArrayList<String>();
		bankClientsToVerify = new ArrayList<String>();
		
	}
	
	public ArrayList<String> getBankAccountsToVerify() {
		return (ArrayList<String>) bankAccountsToVerify;
		
		}
	
	public ArrayList<String> getBankClientsToVerify() {
		return (ArrayList<String>) bankClientsToVerify;
		
		}

}
