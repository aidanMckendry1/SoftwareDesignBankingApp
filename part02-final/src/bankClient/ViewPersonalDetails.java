package bankClient;

public class ViewPersonalDetails extends ViewDetails{

	public ViewPersonalDetails(String username,String password) {
		super(username,password);
	}
	
	public void viewPersonalDetails() {
		String[] personalDetails = {"Username", "Password", "Name", "Address", "Birth Date"};
		String[] details = getDetailsToView();
		formatOutput(personalDetails, details);
	}
	
	private String[] getDetailsToView() {
		String[] details = {bankClient.getUsername(), bankClient.getPassword(), bankClient.getName(),
				bankClient.getAddress(), bankClient.getBirthDate().toString()};
		return details;
	}
	
}
