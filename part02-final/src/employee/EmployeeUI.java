package employee;
import java.util.List;

import java.util.Scanner;

import bankClient.BankClient;
import menu.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeUI {
	static Employee employee;
	static EmployeeDictionarySingleton empDict = EmployeeDictionarySingleton.getInstance();

	public static void main(String[] args) {
		addExampleEmployee();
		end: while(true) {
			String login = getLoginDetails();
			boolean loginValid = validateLogin(login);
			if (loginValid == true) {
				employee = empDict.getEmployee(login);
				addExampleAppointments();
				while(true) {
					String[] appointmentRequests = employee.getAppointmentsArray();
					int choice = EmployeeMenu.getIntChoice(appointmentRequests, "Choose an appointment to book/decline");
					String chosenDate = appointmentRequests[choice-1];
					String[] dateChoices = {"Confirm", "Decline", "Cancel Choice"};
					String bookChoice = Menu.getUserChoice(dateChoices, "Book appointment?");
					if (bookChoice.contentEquals(dateChoices[0])) {
						employee.removeUnscheduledAppointment(choice-1);
						employee.addScheduledAppointment(chosenDate);
						System.out.println("Appointment has been created.");
					}
					else if (bookChoice.contentEquals(dateChoices[1])) {
						employee.removeUnscheduledAppointment(choice-1);
						System.out.println("Appointment has been removed.");
					}
					if (employee.getAppointmentsArray().length == 0) {
						System.out.println("No more pending appointments");
						break end;
					}
				}
			}
			else {
				System.out.println("Please enter valid login details");
			}
		}
	}
	private static String getLoginDetails() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String name = in.nextLine();
		return name;
	}
	private static boolean validateLogin(String name) {
		List<Employee> employeeArray = empDict.getEmployeeList();
		for (Employee emp : employeeArray) {
			if (emp.getName().contentEquals(name)) {
				return true;
			}
		}
		return false;
	}
	private static void addExampleEmployee() {
		empDict.addEmployee(new Employee("Mary"));
	}
	private static void addExampleAppointments() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try{
			Date egDate = dateFormat.parse("27/09/2030");
			employee.addAppointmentDate(egDate, new BankClient("user1", "pass1", "name", "address", egDate));
			
			Date egDate2 = dateFormat.parse("27/03/2010");
			employee.addAppointmentDate(egDate2, new BankClient("user2", "pass2", "name", "address", egDate2));
		}catch (ParseException e) {
			 e.printStackTrace();
		}
	}

}
