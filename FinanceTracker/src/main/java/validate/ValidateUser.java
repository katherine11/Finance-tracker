package validate;

public class ValidateUser {
	
	public static boolean isValidPassword(String password) {
		return ifContain6Chars(password);
		
	}

	public static boolean isPasswordStrong(String password) {
		boolean hasPasswordCapitalLetter = false;
		boolean hasPasswordSmallLetter = false;
		boolean hasPasswordNumber = false;
		for (int index = 0; index < password.length(); index++) {
			if (password.charAt(index) >= 'A' && password.charAt(index) <= 'Z') {
				hasPasswordCapitalLetter = true;
			}
			if (password.charAt(index) >= 'a' && password.charAt(index) <= 'z') {
				hasPasswordSmallLetter = true;
			}
			if (password.charAt(index) >= '0' && password.charAt(index) <= '9') {
				hasPasswordNumber = true;
			}
		}
		if (hasPasswordCapitalLetter && hasPasswordSmallLetter && hasPasswordNumber) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidEmail(String email) {
		if (isValidString(email)) {
			int countMonkeys = 0;
			int monkeyIndex = 0;
			for (int index = 0; index < email.length(); index++) {
				if (email.charAt(index) == '@') {
					countMonkeys++;
					monkeyIndex = index;
				}
			}
			int countPoints = 0;
			for (int index = monkeyIndex; index < email.length(); index++) {
				if (email.charAt(index) == '.') {
					countPoints++;
				}
			}
			if (countMonkeys == 1 && countPoints == 1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidUsername(String username) {
		return ifContain6Chars(username);
	}
	
	private static boolean isValidString(String string) {
		if (string != null && string.trim().length() > 0) {
			return true;
		}
		return false;
	}
	
	private static boolean ifContain6Chars(String password) {
		if (isValidString(password)) {
			if (password.trim().length() >= 6){
				return true;
			}	
		}
		return false;
	}

}
