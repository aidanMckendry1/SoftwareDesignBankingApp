package employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDictionarySingleton {
	List<Employee> employeeList;
	private static EmployeeDictionarySingleton singletonInstance = null;
	
	private EmployeeDictionarySingleton() {
		employeeList = new ArrayList<Employee>();
	}
	public static EmployeeDictionarySingleton getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new EmployeeDictionarySingleton();
		}
		return singletonInstance;
	}
	public void addEmployee(Employee employee) {
		String empName = employee.getName();
		if (getEmployee(empName) == null) {
			employeeList.add(employee);
		}
	}
	public Employee getEmployee(String empName) {
		for(int i=0; i<employeeList.size(); i++) {
			if (employeeList.get(i).getName().contentEquals(empName)) {
				return employeeList.get(i);
			}
		}
		return null;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
}
