package bankClient;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import employee.Employee;
import employee.EmployeeDictionarySingleton;
import transactionTemplate.TransactionTemplate;
import validators.Validators;

public class BookAnAppointment extends TransactionTemplate{

	private BankClient client = null;
	private Employee employee = null;
	private EmployeeDictionarySingleton empDict = EmployeeDictionarySingleton.getInstance();
	
	private Date appDate;
	private int appHour;
	private int appMin;
	private int appDay;
	private int appMonth;
	private int appYear;
	private boolean malUser = false;
	
	private String passInput;
	
	
	Scanner in = new Scanner(System.in);

	public BookAnAppointment(BankClient client) {
		this.client = client;
	}

		// print out which employee this has been booked with
	
	public boolean getMalUserStatus() {		
		return malUser ;
	}

	@Override
	protected void provideDetails() {
		inputDate();
		inputPassword();
	}
	
	
	private void inputDate() {
		System.out.println("Enter appointment day of the month");
		if (in.hasNextInt()) {
			this.appDay = in.nextInt();
		}
		else {
			System.out.println("Invalid date parameter");
			in.nextLine();
		}
		
		System.out.println("Enter appointment month");
		if (in.hasNextInt()) {
			this.appMonth = in.nextInt();
		}
		else {
			System.out.println("Invalid date parameter");
			in.nextLine();
		}

		System.out.println("Enter appointment year");
		if (in.hasNextInt()) {
			this.appYear = in.nextInt();
		}
		else {
			System.out.println("Invalid date parameter");
			in.nextLine();
		}
		
		System.out.println("Enter appointment hour");
		if (in.hasNextInt()) {
			this.appHour = in.nextInt();
		}
		else {
			System.out.println("Invalid date parameter");
			in.nextLine();
		}

		System.out.println("Enter appointment minute");
		if (in.hasNextInt()) {
			this.appMin = in.nextInt();
		}
		else {
			System.out.println("Invalid date parameter");
			in.nextLine();
		}

		@SuppressWarnings("deprecation")
		Date appDate = new Date (appYear, appMonth, appDay, appHour, appMin, 0);
		this.appDate = appDate;
		if (!Validators.validateDate(appDate)) {
			this.malUser = true;
		}
	}
	private void inputPassword() {
		in.nextLine();
		System.out.print("Please Enter Your Password: ");
		passInput = in.nextLine();
	}

	@Override
	protected boolean checkDetails() {
		// Validates there are no missing fields or wrong formatted times
		checkPasswordCorrect();
		checkForFreeEmployee();
		checkMalUserStatus();
		if (error != "") {
			return false;
		}
		return true;
	}
	
	private void checkPasswordCorrect() {
		String actualPass = client.getPassword();
		if (!actualPass.contentEquals(passInput)) {
			error += "Invalid Password";
		}
	}
	private void checkForFreeEmployee() {
		List<Employee> employeeArray = empDict.getEmployeeList();
		for (Employee emp : employeeArray) {
			if (emp.appointmentDateCheck(appDate) == true) {
				employee = emp;
			}
		}
		if (employee == null) {
			error += "No available employee for the chosen date";
		}
	}

	private void checkMalUserStatus() {
		if(malUser == true) {
			error += "Malicious user detected";
		}
	}
	
	@Override
	protected void executeTransaction() {
		client.addAppointmentDate(appDate);
		client.addEmployeeDates(employee.getName());
		employee.addAppointmentDate(appDate, client);
	}
}
