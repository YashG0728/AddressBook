package AddressBook;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        //PROVIDE MENU
        boolean exit = true;
        while (exit) {
            System.out.print(" 1.ADD NEW ADDRESS BOOK ");
            System.out.print(" 2.ADD PERSON DETAILS ");
            System.out.print(" 3.DISPLAY CONTACT");
            System.out.print(" 4.DELETE CONTACT");
            System.out.println(" 5.EDIT DETAILS");
            System.out.println(" 0.EXIT ");
            System.out.print("->");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //addAddressBook
                    addressBook.newAddressBook();
                    break;
                case 2: //For add contact
                    addressBook.addContact();
                    break;
                case 3: //For display contacts
                    addressBook.displayContact();
                    break;
                case 4: //For Edit
                    addressBook.editContact();
                    break;
                case 5: //For deleting
                    addressBook.deleteContact();
                    break;
                case 0:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }
        }
    }
}
