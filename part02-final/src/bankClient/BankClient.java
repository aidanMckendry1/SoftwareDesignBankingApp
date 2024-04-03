package bankClient;
import java.util.ArrayList;

import java.util.List;

import person.Person;
import validators.Validators;

import java.util.Date;

public class BankClient extends Person{

	private static int nextID = 1;
	
	private int id;
	private String address;
	private Date birthDate;
	private String username;
	private String password;
	private boolean malUserStatus = false;
	
	private List<String> bankAccountNumbers;
	private List<String> bankAccountTypes;
	private List<Double> bankAccountBalances;
	private List<String> appointmentDates;
	private List<String> employeeDates;
	
	public BankClient() {
		super(null);
		this.id = nextID++;
		this.name = null;
		this.username = null;
		this.password = null;
		this.address = null;
		this.birthDate = null;
		
		bankAccountNumbers = new ArrayList<String>();
		bankAccountTypes = new ArrayList<String>();
		bankAccountBalances = new ArrayList<Double>();
		appointmentDates = new ArrayList<String>();
		employeeDates = new ArrayList<String>();
	}
	
	public BankClient(String username, String password, String name, String address, Date dob) {
		super(name);
		this.id = nextID++;
		this.username = username;
		if(!Validators.validateUsername(username)) {
			this.malUserStatus = true;
		}
		this.password = password;
		if(!Validators.validatePassword(password)) {
			this.malUserStatus = true;
		}
		this.address = address;
		this.birthDate = dob;
		if(!Validators.validateDate(dob)) {
			this.malUserStatus = true;
		}
		
		bankAccountNumbers = new ArrayList<String>();
		bankAccountTypes = new ArrayList<String>();
		bankAccountBalances = new ArrayList<Double>();
		appointmentDates = new ArrayList<String>();
		employeeDates = new ArrayList<String>();
		
	}	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public int getID() {
		return id;
	}
	public boolean getMalUserStatus() {
		return malUserStatus;
	}
	public String[] getBankAccountNumbers() {
		String[] bankAccNums = new String[bankAccountNumbers.size()];
		for (int i=0; i<bankAccountNumbers.size(); i++) {
			bankAccNums[i] = (String)bankAccountNumbers.get(i);
		}
		return bankAccNums;
	}
	public String[] getBankAccountTypes() {
		String[] bankAccTypes = new String[bankAccountTypes.size()];
		for (int i=0; i<bankAccountNumbers.size(); i++) {
			bankAccTypes[i] = (String)bankAccountTypes.get(i);
		}
		return bankAccTypes;
	}
	public String[] getBankAccountBalances() {
		String[] bankAccBalan = new String[bankAccountBalances.size()];
		for (int i=0; i<bankAccountBalances.size(); i++) {
			bankAccBalan[i] = (String)bankAccountBalances.get(i).toString();
		}
		return bankAccBalan;
	}
	public void addBankAccount(String accountNumber, String accountType) {
		bankAccountNumbers.add(accountNumber);
		bankAccountTypes.add(accountType);
		bankAccountBalances.add(0.0);
	}
	public void removeBankAccount(int index) {
		bankAccountNumbers.remove(index);
		bankAccountTypes.remove(index);
		bankAccountBalances.remove(index);
	}
	public int getAccountIndex(String accNum) {
		String[] accountNumbers = this.getBankAccountNumbers();
		for(int i=0; i<accountNumbers.length; i++) {
			if (accountNumbers[i].contentEquals(accNum)) {
				return i;
			}
		}
		return -1;
	}
	public void setUsername(String newUsername) {
		this.username = newUsername;
		
	}
	public void setPassword(String newPassword) {
			this.password = newPassword;
		}
	public void setName(String newName) {
		this.name = newName;
	}
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	public void setBirthdate(Date newBirthDate) {
		this.birthDate = newBirthDate;
	}
	public void addAppointmentDate(Date appDetails) {
		appointmentDates.add(appDetails.toString());
	}
	public void addEmployeeDates(String empDetails) {
		employeeDates.add(empDetails);
	}
	public void addMoneyTo(double amt, String accNum) {
		int accI = this.getAccountIndex(accNum);
		double oldAccBal = Double.parseDouble(this.getBankAccountBalances()[accI]);
		oldAccBal+=amt;
		
		bankAccountBalances.set(accI, oldAccBal);
	}
	public void subMoneyFrom(double amt, String accNum) {
		int accI = this.getAccountIndex(accNum);
		double oldAccBal = Double.parseDouble(this.getBankAccountBalances()[accI]);
		oldAccBal-=amt;
		
		bankAccountBalances.set(accI, oldAccBal);
	}
	
	//TESTING PURPOSES ONLY
	public void setBalance(double i, int accNum) {
		bankAccountBalances.set(accNum, i);
	}

	@Override
	public String toString() {
		String details = "ClientID = " + id + "\n" + "\t"
				+ "Name = " + name + "\n" + "\t"
				+ "Username = " + username + "\n" + "\t"
				+ "Password = " + password + "\n" + "\t";
		if(malUserStatus) {
			details += "Malicious user detected";
		}
		details+= "\n";
		return details;
	}
}
