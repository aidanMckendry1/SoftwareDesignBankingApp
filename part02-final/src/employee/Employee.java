package employee;
import java.util.ArrayList;



import java.util.Date;

import bankClient.BankClient;
import person.Person;
import validators.Validators;

public class Employee extends Person{
	
	private boolean malUserStatus = false;
	
	public ArrayList<String> appointments;
	public ArrayList<String> appointmentsClient;
	private ArrayList<String> scheduledAppointments;
	private ArrayList<String> scheduledAppointmentsClient;
	public ArrayList<String> clients;
	
	public Employee(String name) {
		super(name);
		
		appointments = new ArrayList<String>();
		appointmentsClient = new ArrayList<String>();
		scheduledAppointments = new ArrayList<String>();
		scheduledAppointmentsClient = new ArrayList<String>();
		clients = new ArrayList<String>();		
	}

	public String getName() {
		return name;
	}
	public boolean getMalUserStatus() {
		return malUserStatus;
	}

	public ArrayList<String> getAppointments() {
		return appointments;
	}
	public ArrayList<String> getScheduledAppointments() {
		return scheduledAppointments;
	}
	public String[] getAppointmentsArray() {
		String[] appArray = new String[appointments.size()];
		for (int i=0; i<appointments.size(); i++) {
			appArray[i] = appointments.get(i) + "\n" + "\t" + appointmentsClient.get(i);
		}
		return appArray;
	}
	
	public void removeUnscheduledAppointment(int index) {
		appointments.remove(index);
	}
	public void addScheduledAppointment(String date) {
		scheduledAppointments.add(date);
	}

	public ArrayList<String> getClients() {
		return clients;
	}
	public boolean appointmentDateCheck(Date date) {
		String error = isEmployeeAvailable(date);
		if (error!=null) {
			System.out.println("Error: " + error);
			return false;
		}
		return true;
	}
	private String[] getScheduledAppointmentsArray() {
		String[] appArray = new String[scheduledAppointments.size()];
		for (int i=0; i<scheduledAppointments.size(); i++) {
			appArray[i] = scheduledAppointments.get(i);
		}
		return appArray;
	}
	private String isEmployeeAvailable(Date date) {
		String[] appointments = getScheduledAppointmentsArray();
		for (String appDate : appointments) {
			if (date.toString().equals(appDate)) {
				return "Date is already taken";
			}
		}
		return null;
	}
	public void addAppointmentDate(Date date, BankClient bc) {
		appointments.add(date.toString());
		if(!Validators.validateDate(date)) {
			this.malUserStatus = true;
			System.out.println("Malicious user detected, date after today \n");
		}
		appointmentsClient.add(bc.toString());
	}
}
