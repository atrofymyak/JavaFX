package validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationService {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static String validateUser(String id, String name, String mail, List<String> cardIds) {
		String error = "";

		if (id == null || id.isEmpty()) {
			error = "id can not be empty";
		} else if (name == null || name.isEmpty()) {
			error = "name can not be empty";
		} else if (mail == null || mail.isEmpty()) {
			error = "mail can not be empty";
		} else if (cardIds == null || cardIds.isEmpty()) {
			error = "card id can not be empty";
		} else if (mail != null && !mail.isEmpty()) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(mail);
			if (!matcher.matches()) {
				error = "invalid mail";
			}
		}

		return error;
	}

	public static String validateCredit(String card, String credit) {
		String error = "";

		if (card == null || card.isEmpty()) {
			error = "Please select card";
		} else if (credit == null || credit.isEmpty()) {
			error = "Please fill credit value";
		} else if (credit != null && !credit.isEmpty() && !isNumeric(credit)) {
			error = "Please fill numeric credit value";
		}

		return error;
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
