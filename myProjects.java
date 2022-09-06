import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Formatter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class myProjects extends userInputs {

	public int findProject(String projectInfo) {
		// A line count set to run through the text file to find the project.
		String[] info = new String[10];
		int lineCount = 1;

		// A new file object is created and a while loop used to run through lines in
		// the test file.
		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {

				info = projectFile.nextLine().split(", ");

				if ((info[0].equalsIgnoreCase(projectInfo)) || (info[1].equalsIgnoreCase(projectInfo))) {
					break;

				} else {
					lineCount++; // Line count incremented whilst match not made.

				}
			}
		} catch (FileNotFoundException e) { // Try-catch block used to handle errors.
			System.out.println("Error.");

		}
		return lineCount; // Integer line count returned.
	}

	public void viewProjects() {
		String[] info = new String[10]; // String array set to store project info from lines in text file.

		// A file object is created and a while loop used to run through each line in
		// the CurrentProjects.txt file.
		try {
			File searchFile = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(searchFile);

			while (projectFile.hasNextLine()) {
				info = projectFile.nextLine().split(", ");
				String projectNum = info[0];
				String projectName = info[1];
				String building = info[2];
				String address = info[3];
				String erfNum = info[4];
				String totalCost = info[5];
				String paid = info[6];
				String deadline = info[7];
				String Completion = info[8];
				String Status = info[9];

				// Project info displayed for each line in the file.
				System.out.println("\nProject Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + building + "\nPhysical Address: " + address + "\nERF Number: " + erfNum
						+ "\nTotal Fee: R"
						+ totalCost + "\nAmount Paid: R" + paid + "\nDeadline: " + deadline + "\nCompletion Date: "
						+ Completion + "\nProject Status: " + Status);
			}
		} catch (FileNotFoundException e) { // Try-catch block used to handle errors.
			System.out.println("Error.");
		}

	}

	public void updateProject(int editChoice, int lineCount) {
		// An ArrayList is set to store each line (i.e. each project) from the text file
		// with current projects.
		ArrayList<String> infoArray = new ArrayList<String>();
		String[] info = new String[10];
		int findLine = 1;

		// A file object is created and a while loop used to run through each line of
		// the text file.
		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {
				infoArray.add(projectFile.nextLine()); // Each line added to the infoArray.

			}

		} catch (FileNotFoundException e) { // try-catch block used to handle errors.
			System.out.println("Error.");
		}

		// Another try-catch block is now used to create another file object.
		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {
				if (findLine == lineCount) {
					info = projectFile.nextLine().split(", ");

				} else if (findLine != lineCount) { // Line count incremented if match is not made.
					findLine++;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error.");
		}

		if (editChoice == 1) {
			System.out.println("Please enter a new project due date: ");
			String newDeadline = stringCheck("due date");
			info[7] = newDeadline;
			String newInfo = Arrays.toString(info);
			String changeLine = newInfo.replace("[", "");
			String newLine = changeLine.replace("]", "");
			infoArray.set(lineCount - 1, newLine);

		} else if (editChoice == 2) {
			System.out.println("Please enter a new total amount of the fee paid to date:");
			double newAmount = doubleCheck("new total amount");
			info[6] = Double.toString(newAmount);
			String newInfo = Arrays.toString(info);
			String changeLine = newInfo.replace("[", "");
			String newLine = changeLine.replace("]", "");
			infoArray.set(lineCount - 1, newLine);

		}

		// Each element, or line of project info, of the updated array
		try {
			Formatter F = new Formatter("C:\\CurrentProjects.txt");
			for (String element : infoArray) {
				F.format("%s", element + "\r\n");
			}
			System.out.println("Project successfully updated."); // Successful message displayed.
			F.close();

		} catch (Exception e) {
			System.out.println("Error."); // try-catch block used to handle errors.

		}
	}

	public void finaliseProject(int lineCount) {
		// An ArrayList is set to store each line (i.e. each project) from the text file
		// with current projects.
		ArrayList<String> infoArray = new ArrayList<String>();
		String[] info = new String[10];
		int findLine = 1;

		// A file object is created and a while loop used to run through each line of
		// the text file.
		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {
				infoArray.add(projectFile.nextLine());

			}
		} catch (FileNotFoundException e) {
			System.out.println("Error.");

		}

		// Another try-catch block is now used to create another file object.
		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {
				if (findLine == lineCount) {
					info = projectFile.nextLine().split(", ");

				} else {
					findLine++; // Line count incremented if match is not made.

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error.");
		}

		// Each index of the split line is then assigned to a new variable.
		String projectNum = info[0];
		String projectName = info[1];
		String building = info[2];
		String address = info[3];
		String erfNum = info[4];
		String deadline = info[7];
		String Completion;
		String Status = "Finalised"; // Status of project set to 'finalised'.

		double totalCost = Double.parseDouble(info[5]);
		double paid = Double.parseDouble(info[6]);

		// If the project has been paid in full, no invoice is generated (i.e. totalFee
		// = amountPaid).
		if (totalCost == paid) {
			System.out.println("The project has been paid in full. No invoice generated.");
			System.out.println(
					"Please add a completion date for this project (e.g. day, month, year, 3 December 2020): ");

			Completion = stringCheck("completion date");

			// If there is still an amount outstanding, the user is prompted for customer
			// details to generate an invoice.
		} else {
			System.out.println("Your invoice will be generated with the following details: ");
			System.out.println("\nPlease enter the customer's full name: ");

			String name = stringCheck("customer's full name");

			String personType2 = "Customer";

			System.out.println("\nPlease enter the customer's contact number: ");
			int number2 = intCheck("customer's contact number");

			System.out.println("\nPlease enter the customer's email address: ");
			String email2 = stringCheck("customer's email address");

			System.out.println("\nPlease enter the customer's physical address: ");
			String address2 = stringCheck("customer's physical address");

			System.out.println(
					"Please add a completion date for this project (e.g. day, month, year, 3 December 2020): ");
			Completion = stringCheck("completion date");

			// A new person object is created with the gathered info.
			Customer person = new Customer(name, personType2, number2, email2, address2);

			System.out.println("\nPlease view your invoice details below: ");
			System.out.println(person.DisplayCustomer());
			System.out.println("\nAmount still owed: R" + (totalCost - paid) + "\n");

		}
		// Finalised project details also displayed to the user in a clear format.
		System.out.println(
				"Finalised Project Details: \n" + "Project Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + building + "\nPhysical Address: " + address + "\nERF Number: " + erfNum
						+ "\nTotal Fee: R"
						+ totalCost + "\nAmount Paid: R" + paid + "\nDeadline: " + deadline + "\nCompletion Date: "
						+ Completion + "\nProject Status: " + Status);

		infoArray.remove(lineCount - 1); // infoArray updated by removing the project from the current projects list.

		// The updated infoArray is then written to the CurrentProjects.txt file.
		try {
			Formatter F = new Formatter("C:\\CurrentProjects.txt");
			for (String element : infoArray) {
				F.format("%s", element + "\r\n");

			}
			System.out.println("Project successfully finalised."); // Successful message displayed.
			F.close();

		} catch (Exception e) {
			System.out.println("Error."); // try-catch block used to handle errors.

		}
		String projectComplete = projectNum + ", " + projectName + ", " + building + ", " + address + ", "
				+ erfNum + ", " + totalCost + ", " + paid + ", " + deadline + ", " + Completion + ", " + Status;

		// A file is then created to store the completed/ finalised projects, if it does
		// not already exist.
		try {
			File newFile = new File("C:\\CompletedProjects.txt"); // Please specify the file path for your desired
																	// output.

			if (newFile.createNewFile()) {
				System.out.println("File created: " + newFile.getName());

			} else {
				System.out.println("File already exists.");

			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}

		// Thereafter, the string of project completed info is written to the
		// CompletedProjects.txt file in append mode.
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("C:\\CompletedProjects.txt", true)); // Please
																										// specify the
																										// file path for
																										// your desired
																										// output.
			out.write(projectComplete + " \r\n");
			out.close();
			System.out.println("Project sucessfully finalised."); // Successful message displayed.

		} catch (IOException e) {
			System.out.println("exception occoured" + e);

		}
	}

	public void dateCheck(String projectType) throws ParseException {

		String[] info = new String[10];
		int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		String[] monthsofYear = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		int monthNum = 0;

		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			while (projectFile.hasNextLine()) {
				info = projectFile.nextLine().split(", ");
				String projectNum = info[0];
				String projectName = info[1];
				String building = info[2];
				String address = info[3];
				String erfNum = info[4];
				String totalCost = info[5];
				String paid = info[6];
				String Completion = info[8];
				String finalise = info[9];
				String deadline = info[7];
				String[] dateInfo = deadline.split(" ");
				int day = Integer.parseInt(dateInfo[0]);
				String monthInfo = dateInfo[1];
				String month = (monthInfo.substring(0, 2));
				int year = Integer.parseInt(dateInfo[2]);

				for (int index = 0; index < monthsofYear.length; index++) {
					if (month.equalsIgnoreCase(monthsofYear[index])) {
						monthNum = months[index];

					}
				}
				String current = "" + java.time.LocalDate.now(); // Getting the current date and storing it as a string.

				// Creating a new simple date format object.
				SimpleDateFormat dateObj = new SimpleDateFormat("yyyy-MM-dd");

				// Dates d1 and d2 are then created by parsing string info from 'current' date
				// and date info gathered from the file above.
				Date d1 = dateObj.parse(current);
				Date d2 = dateObj.parse(day + "-" + monthNum + "-" + year);

				// Setting a string to display project details.
				String projectDetails = "\nProject Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + building + "\nPhysical Address: " + address + "\nERF Number: " + erfNum
						+ "\nTotal Fee: R"
						+ totalCost + "\nAmount Paid: R" + paid + "\nDeadline: " + deadline + "\nCompletion Date: "
						+ Completion + "\nProject Status: " + finalise;

				// If the current date is before the deadline date and the user has chosen to
				// view 'incomplete' projects,
				// Those specific projects from each line of the text file are displayed.
				if ((d1.compareTo(d2) > 0) && (projectType.equalsIgnoreCase("incomplete"))) {
					System.out.println(projectDetails);

					// Otherwise, if the current date is past the deadline and the user has chosen
					// to view 'overdue' projects,
					// Those specific projects from each line of the text file are displayed.
				} else if ((d1.compareTo(d2) < 0) && (projectType.equalsIgnoreCase("overdue"))) {
					System.out.println(projectDetails);

				}
			}
		} catch (FileNotFoundException e) { // try-catch block used to handle errors.
			System.out.println("Error.");

		}
	}
}
