public class Customer {
    // Listing variable attributes for the 'Constractor' class.
    String name;
    String personType2;
    int number2;
    String email2;
    String address2;

    // Writing a constructor method to create a constractor object.
    public Customer(String name, String personType2, int number2, String email2, String address2) {
        this.name = name;
        this.personType2 = personType2;
        this.number2 = number2;
        this.email2 = email2;
        this.address2 = address2;

    }

    public String getName() {
        return name;
    }

    public String getPersonType2() {
        return personType2;
    }

    public int getNumber2() {
        return number2;
    }

    public String getEmail2() {
        return email2;
    }

    public String getAddress2() {
        return address2;
    }

    public String DisplayCustomer() {

        String output = "Name: " + name;
        output += "\nNumber: " + number2;
        output += "\nEmail Address: " + email2;
        output += "\nPhysical Address: " + address2;

        return output;
    }

}
