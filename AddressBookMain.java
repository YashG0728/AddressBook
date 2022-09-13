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
            System.out.print(" 2.DISPLAY ADDRESS BOOK");
            System.out.print(" 3.ADD PERSON DETAILS ");
            System.out.print(" 4.DISPLAY CONTACT");
            System.out.print(" 5.DELETE CONTACT");
            System.out.println(" 6.EDIT DETAILS FROM ADDRESS BOOK");
            System.out.println(" 7.SEARCH BY CITY");
            System.out.print(" 8.SEARCH BY STATE");
            System.out.print(" 9.VIEW BY CITY");
            System.out.print(" 10.VIEW BY STATE");

            System.out.println(" 0.EXIT ");
            System.out.print("->");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //addAddressBook
                    addressBook.newAddressBook();
                    break;
                case 2: //Display details
                    addressBook.displayAddressBook();
                    break;
                case 3: //For add contact
                    addressBook.addContact();
                    break;
                case 4: //For display contacts
                    addressBook.displayContact();
                    break;
                case 5: //For deleting
                    addressBook.deleteContact();
                    break;
                case 6://Edit details from book
                    addressBook.editAddressBook();
                    break;
                case 7://Search by city
                    addressBook.searchCity();
                    break;
                case 8://Search by state
                    addressBook.searchState();
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

