package AddressBook;

import java.util.*;

public class AddressBook {

    Scanner sc = new Scanner(System.in);
    ArrayList<PersonInfo> list = new ArrayList<>();
    Map<String, ArrayList<PersonInfo>> map = new HashMap<>();

    void addContact() {

        PersonInfo personInfo = new PersonInfo();

        System.out.println("Enter FirstName : ");
        String firstName = sc.nextLine();

        System.out.println("Enter lastName : ");
        String lastName = sc.nextLine();

        System.out.println("Enter address : ");
        String address = sc.nextLine();

        System.out.println("Enter city : ");
        String city = sc.nextLine();

        System.out.println("Enter state : ");
        String state = sc.nextLine();

        System.out.println("Enter emailID : ");
        String emailId = sc.nextLine();

        System.out.println("Enter zip : ");
        String zip = sc.nextLine();

        System.out.println("Enter phoneNumber : ");
        String phoneNumber = sc.nextLine();

        personInfo.setFirstName(firstName);
        personInfo.setLastName(lastName);
        personInfo.setAddress(address);
        personInfo.setCity(city);
        personInfo.setState(state);
        personInfo.setEmailID(emailId);
        personInfo.setZip(zip);
        personInfo.setPhoneNumber(phoneNumber);


        list.add(personInfo);
        System.out.println(personInfo);
        System.out.println("Contact Added Successfully");
    }

    void displayContact() {
        System.out.println("------------------------------------------");
        for (PersonInfo display : list) {
            System.out.println(display);
        }
        System.out.println("------------------------------------------");
    }
}
    