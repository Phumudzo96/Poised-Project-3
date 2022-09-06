import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Project extends userInputs {

    private int projectNum;
    private String projectName;
    private String building;
    private String address;
    private String erfNum;
    private double totalCost;
    private double paid;
    private String deadline;
    private String Completion;
    private String Status;

    public void addProject() {
        // Completion and Status variables set to negative as this is a newly added
        // project.
        Completion = "None";
        Status = "Not finalised";

        // User prompted for input regarding project information.
        System.out.println("\nPlease enter the project number: ");
        projectNum = intCheck("project number");

        System.out.println("Please enter the project name: ");
        projectName = stringCheck("project name");

        System.out.println("Please enter the building type: ");
        building = stringCheck("building type");

        System.out.println("Please enter the physical address for the project: ");
        address = stringCheck("physical address");

        System.out.println("Please enter the ERF number: ");
        erfNum = stringCheck("ERF number");

        System.out.println("Please enter the total fee charged for the project: ");
        totalCost = doubleCheck("total fee");

        System.out.println("Please enter the total amount paid to date: ");
        paid = doubleCheck("total amount");

        System.out.println("Please enter the project deadline (e.g. day, month, year: 3 November 2020) : ");
        deadline = stringCheck("project deadline");

        // New project object dispayed to the user.
        System.out.println("\nProject Number: " + projectNum + "\nProjectName: " + projectName
                + "\nBuilding Type: " + building + "\nPhysical Address: " + address + "\nERF Number: " + erfNum
                + "\nTotal Fee: R"
                + totalCost + "\nAmount Paid: R" + paid + "\nDeadline: " + deadline + "\nCompletion Date: " + Completion
                + "\nProject Status: " + Status);

        String projectInfo = projectNum + ", " + projectName + ", " + building + ", " + address
                + ", " + erfNum + ", " + totalCost + ", " + paid + ", " + deadline + ", " + Completion
                + ", " + Status;

        // Writing the string 'projectInfo' to the CurrentProjects.txt file.
        // Try-catch block used to deal with any errors encountered.
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("C:\\CurrentProjects.txt", true));

            out.write(projectInfo + "\r\n");
            out.close();
            System.out.println("Your project was successfully added.");

        } catch (IOException e) {
            System.out.println("exception occoured" + e);

        }
    }

}