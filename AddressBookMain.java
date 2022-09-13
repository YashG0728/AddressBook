package AddressBook;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        //PROVIDE MENU
        boolean exit = true;
        while (exit) {
            System.out.print(" 1.ADD PERSON DETAILS ");
            System.out.print(" 2.DISPLAY CONTACT");
            System.out.println(" 0.EXIT ");
            System.out.print("->");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //For add contact
                    addressBook.addContact();
                    break;
                case 2: //For display contacts
                    addressBook.displayContact();
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

