package AddressBook;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        boolean exit = true;
        while (exit) {
            System.out.print(" 1.ADD PERSON DETAILS ");
            System.out.print(" 2.DISPLAY CONTACT ");
            System.out.print(" 3.EDIT DETAILS ");
            System.out.print(" 4.DELETE CONTACT ");
            System.out.println(" 0.EXIT ");
            System.out.println("->");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //For add contact
                    addressBook.addContact();
                    break;
                case 2: //For display contacts
                    addressBook.displayContact();
                    break;
                case 3: //Edit details
                    addressBook.editContact();
                    break;
                case 4://For deleting
                    addressBook.deleteContact();
                    break;
                case 0://For exit
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }
        }
    }
}

