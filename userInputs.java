import java.util.Scanner;

public class userInputs {

	public static int intCheck(String type) {

		while (true) { // While loop repeatedly re-prompts for input until correct.
			Scanner inputNum = new Scanner(System.in);
			String number = inputNum.nextLine();

			try {
				int output = Integer.parseInt(number); // Attempting to parse the input to an integer (i.e. checking for
														// correct input).
				return output;

			} catch (NumberFormatException ex) {
				System.out.println("Incorrect entry. Please re-enter the " + type + ": \n"); // Error message displayed
																								// if parsing is not
																								// possible.

			}
		}
	}

	public static String stringCheck(String type) {

		while (true) { // While loop repeatedly re-prompts for input until correct.
			Scanner inputUser = new Scanner(System.in);
			String input = inputUser.nextLine();

			if ((input == null) || (input.length() > 150)) { // Checking if the input is empty, too short, or too long.
				System.out.println("Incorrect entry. Please re-enter the " + type + ": \n");

			} else {
				return input; // Returning the user's correctly inputed string.

			}
		}
	}

	public static double doubleCheck(String type) {

		while (true) { // While loop repeatedly re-prompts for input until correct.
			Scanner inputDouble = new Scanner(System.in);
			String number = inputDouble.nextLine();

			try {
				double output = Double.parseDouble(number); // Attempting to parse the input to a double (i.e. checking
															// for correct input).
				return output;

			} catch (NumberFormatException ex) {
				System.out.println("Incorrect entry. Please re-enter the " + type + ": \n"); // Error message displayed
																								// if parsing is not
																								// possible.

			}
		}
	}

}
