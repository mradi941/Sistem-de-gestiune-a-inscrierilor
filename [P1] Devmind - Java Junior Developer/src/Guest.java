
public class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	
    // Guest constructor 
	public Guest(String firstName,String lastName,String email,String phoneNumber) {
		
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
	}


	
	// Getter and Setter 
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
   
	public String getFullName() {
		return ""+firstName+" "+lastName;
	}


	
	
	
	
}
