import java.util.*;

public class Driver{

	

	public static void main(String args[]){
		String email, password;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Email: ");
		email = sc.nextLine();
		System.out.println("Enter Password: ");
		password = sc.nextLine();

		Validate validator = new Validate(email, password);
		validator.validateForm();	
	}
	

}