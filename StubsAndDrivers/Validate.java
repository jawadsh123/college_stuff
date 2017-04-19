
class Validate{

	String email, password;
	public Validate(String email, String password){
		this.email = email;
		this.password = password;
	}

	public void validateForm(){
		if(validateEmail(email) && validatePassword()){
			System.out.println("Validated");
		}else{
			System.out.println("Validation Failed please check your email and password");
		}
	}

	public boolean validateEmail(String email){
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	//This is a stub
	public boolean validatePassword(){
		return true;
	}
}