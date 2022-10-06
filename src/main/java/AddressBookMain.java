import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        //PROVIDE MENU
        boolean exit = true;
        while (exit) {
            System.out.print(" 1.ADD NEW ADDRESS BOOK ");
            System.out.print(" 2.OPEN OLD ADDRESS BOOK ");
            System.out.print(" 3.DISPLAY ADDRESS BOOK ");
            System.out.print(" 4.EDIT DETAILS FROM ADDRESS BOOK ");
            System.out.print(" 5.SEARCH BY CITY ");
            System.out.print(" 6.SEARCH BY STATE ");
            System.out.println(" 7.VIEW BY CITY ");
            System.out.print(" 8.VIEW BY STATE ");
            System.out.print(" 9.COUNT BY CITY ");
            System.out.print(" 10.SORT BY PERSON NAME ");
            System.out.print(" 11.SORT BY CITY ");
            System.out.print(" 12.SORT BY STATE ");
            System.out.print(" 13.READ FILE USING FILE IO ");
            System.out.print(" 14.WRITE FILE USING CSV ");
            System.out.println(" 0.EXIT ");
            System.out.print("->");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //addAddressBook
                    addressBook.newAddressBook();
                    break;
                case 2: //addAddressBook
                    addressBook.openAddressBook();
                    break;
                case 3: //Display details
                    addressBook.displayAddressBook();
                    break;
                case 4://Edit details from book
                    addressBook.editAddressBook();
                    break;
                case 5://Search by city
                    addressBook.searchCity();
                    break;
                case 6://Search by state
                    addressBook.searchState();
                    break;
                case 7: //View all persons in same city
                    addressBook.viewByCity();
                    break;
                case 8: //View all persons in same state
                    addressBook.viewByState();
                    break;
                case 9: //Count by city
                    addressBook.countByCity();
                    break;
                case 10: //Sort by city
                    addressBook.sortByCity();
                    break;
                case 11: //Sort person by name in multiple address book
                    addressBook.sortByPersonNameInBooks();
                    break;
                case 12: //Read data from File IO
                    addressBook.ReadFileIO();
                    break;
                case 13: //Read data from csv
                    addressBook.readCSV();
                    break;
                case 14: //Read data from csv
                    addressBook.writeAddressBookJson();
                    break;
                case 15:
                    addressBook.readAddressBookJson();
                    break;
                case 16:
                    addressBook.retrieveData();
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

