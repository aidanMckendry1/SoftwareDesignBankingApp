package admin;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminUI {
	static Admin admin;
	static String[] options ={"Verify bank account", "Do not verify bank account"};
	static private ArrayList <AdminNotification> notifications = new ArrayList <AdminNotification>();
	public static String choice;
	

	public static void main(String[] args) {
		notifications.add(new AdminNotification("user","savings"));
		notifications.add(new AdminNotification("$user2","primary"));
	
		while(true) {
			// Print out notification, (notification object tostring?)
			// Call verify bank account 
			System.out.println("<----" + "Welcome to the admin portal" + "---->");
			System.out.println();
			System.out.println("1) Verify bank accounts");
			System.out.println("2) Exit");
			
			Scanner in = new Scanner(System.in);

			if (in.hasNextInt()){
				choice = in.nextLine();
			}
			else {
				System.out.println("Enter a valid choice");
			}
			
			if (choice.equals("1")) {
				for (AdminNotification notification: notifications) {
					System.out.println(notification);
					if(notification.getMalUserStatus()) {
						System.out.println("\tMalicious user detected.");
					}
					System.out.println();
				}
				//VerifyBankAccount newVerifyBankAccount = new VerifyBankAccount(notification);
				//newVerifyBankAccount.verifyBankAccount();
				for (AdminNotification notification: notifications) {
					System.out.println("Account number: " + String.valueOf(notification.getAccountNumber()));
					System.out.println("1. Verify the opening \n2. Do not verify the opening \nProvide choice:\n >");
					
					String notificationNo = in.nextLine();
					if (notificationNo.equals("1") || notificationNo.equals("2")) {
						boolean verified = notificationNo.equals("1");
						System.out.println("Account number: " + String.valueOf(notification.getAccountNumber()) + ", Verified = " + String.valueOf(verified) + "\n");
					}
					else {
						System.out.println("Invalid choice");
					}
				}
				System.out.println("No more pending bank accounts.");
				break;
			}
			else if (choice.equals("2")) {
				System.out.println("Exiting system...");
				break;
			}
			else {
				System.out.print("Invalid choice");
			}
		}	
	}	
}
