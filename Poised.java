import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;

/**
 * my poised subclass is the one that inherit methods from the userInputs super
 * class.
 * <p>
 * This is where my main program runs and even calls other methods.
 * In this method we run the main meun for the user.
 * 
 * @author Phumudzo Mbelengwa
 */
public class Poised extends userInputs {

	/**
	 * This is the main meun that runs the poised method.
	 * <p>
	 * 
	 * @param args java command line arguments
	 * @throws ParseException occurs if a date string is in the wrong format to be
	 *                        parsed
	 */

	public static void main(String[] args) throws ParseException {

		/*
		 * A welcome message is displayed for the user.
		 * Thereafter, the system creates a 'CurrentProjects.txt.' file to store project
		 * info, if it does not exist already.
		 */

		System.out.println("Welcome to the Poised!!! \n");

		try {
			File newFile = new File("C:\\CurrentProjects.txt");

			if (newFile.createNewFile()) {
				System.out.println("File created: " + newFile.getName());

			} else {
				System.out.println("This file has already been created.");
			}
		} catch (IOException e) {
			System.out.println("Sorry! An error has been found.");
			e.printStackTrace();
		}

		/*
		 * The main meun appear for the user to choose from.
		 * A file check is created to check if the Project in question has been created
		 * or not.
		 * When you select "2", you then create a new project
		 * In this method we'll be able to call upon the other methods that i have
		 * created. EG; addProject() from Project.
		 * The main meun will keep on appearing unless the user selects "8" to exit the
		 * program
		 */

		while (true) {
			System.out.println("\nPlease choose a number option from the menu below: "
					+ "\n1. View Existing Projects"
					+ "\n2. Add a New Project"
					+ "\n3. Update Existing Project Info"
					+ "\n4. Finalize a Project"
					+ "\n5. View Incomplete Projects"
					+ "\n6. View Overdue Projects"
					+ "\n7. Find a Project"
					+ "\n8. Exit program");

			int choice = intCheck("menu choice"); // Checking integer input with method from the userInputs class.
			boolean projects = fileCheck();

			if ((projects == false) && ((choice == 1) || (choice == 3) || (choice == 4) || (choice == 5)
					|| (choice == 6) || (choice == 7))) {
				System.out.println(
						"There are no projects that have been created. \nPlease choose option '2' from the menu to add a new project.");

			} else if ((projects == true) && (choice == 1)) { // Option 1 allows the user to view projects.
				myProjects view = new myProjects();
				view.viewProjects();

			} else if (choice == 2) { // Option 2 allows the user to add a new project.
				Project add = new Project();
				add.addProject();

			} else if ((projects == true) && (choice == 3)) { // Option 3 allows the user to edit a project.

				System.out.println(
						"Please enter the project number or name of the project you wish to make an update on: \n");
				String projectInfo = stringCheck("project number");
				System.out.println("Would you like to:" +
						"\n1. Edit the project due date or" +
						"\n2. Edit the total amount paid of the fee to date?" + // Edit options displayed.
						"\nChoose either 1 or 2");

				int editChoice = intCheck("edit choice");
				myProjects obj1 = new myProjects();
				int lineCount = obj1.findProject(projectInfo);
				obj1.updateProject(editChoice, lineCount); // findProject() and updateProject() methods called on from
															// myProjects class.

			} else if ((projects == true) && (choice == 4)) { // Option 4 allows the user to finalise a project.
																// Finalised projects are stored in a new text file
																// 'CompletedProjects.txt'.
																// Specify the file path for this file in the
																// finaliseProject() method in myProjects class.

				System.out.println("Please enter the project number or name of the project you wish to finalise: \n");
				String projectInfo = stringCheck("project name OR number");
				myProjects obj2 = new myProjects();
				int lineCount = obj2.findProject(projectInfo);
				obj2.finaliseProject(lineCount);

			} else if ((projects == true) && (choice == 5)) { // Option 5 allows the user to view incomplete projects.
				myProjects obj3 = new myProjects();
				obj3.dateCheck("incomplete");

			} else if ((projects == true) && (choice == 6)) { // Option 6 allows the user to view overdue projects.
				myProjects obj4 = new myProjects();
				obj4.dateCheck("overdue");

			} else if ((projects == true) && (choice == 7)) { // Option 7 allows the user to find a project to view.

				System.out.println("Please enter the name or project number of the project you wish to find: ");
				String projectInfo = stringCheck("project name OR number");
				myProjects obj5 = new myProjects();
				int lineCount = obj5.findProject(projectInfo); // This method returns a line count integer for the
																// project.

				String[] info = new String[10]; // Setting a string array to store info from the project's line in the
												// text file.
				int findLine = 0; // Setting an integer variable to match the returned line count value.

				try {
					File searchFile = new File("C:\\CurrentProjects.txt");
					Scanner projectFile = new Scanner(searchFile);

					while (projectFile.hasNextLine()) { // Searching for the correct project in the file by line count.

						if (findLine == lineCount) {
							info = projectFile.nextLine().split(", "); // Storing the correct line info into the array.

						} else {
							findLine++;

						}
					}

				} catch (FileNotFoundException e) {
					System.out.println("Sorry! An error has been found.");

				}
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

				// Project information displayed in clear format.
				System.out.println("\nProject Number: " + projectNum + "\nProjectName: " + projectName
						+ "\nBuilding Type: " + building + "\nPhysical Address: " + address + "\nERF Number: " + erfNum
						+ "\nTotal Fee: R"
						+ totalCost + "\nAmount Paid: R" + paid + "\nDeadline: " + deadline + "\nCompletion Date: "
						+ Completion + "\nProject Status: " + Status);

			} else if (choice == 8) { // Option 8 allows the user to exit the program.

				System.out.println("Successfully logged out."); // Successful logout message displayed.
				break;
			}
		}
	}

	/**
	 * The fileCheck method is run to check for existing projects in the
	 * CurrentProjects.txt file.
	 * <p>
	 * It guides the user's menu choices, depending on whether there are existing
	 * projects in the text file.
	 * 
	 * @return returns the boolean value for projectCheck
	 */
	public static boolean fileCheck() {

		boolean projectCheck = false; // Setting boolean value to check the file contents.

		try {
			File projects = new File("C:\\CurrentProjects.txt");
			Scanner projectFile = new Scanner(projects);

			if (projectFile.hasNextLine()) { // If the file contains contents for a project, projectCheck is 'true'.
				projectCheck = true;

			} else {
				projectCheck = false; // If the file does not contain projects, projectCheck is 'false'.

			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry! An error has been found.");

		}
		return projectCheck; // boolean value returned.

	}
}