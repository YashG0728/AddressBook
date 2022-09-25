package AddressBook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AddressBook {
    public int flag = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<PersonInfo> list;
    Map<String, List<PersonInfo>> maps;
    Map<String, List<PersonInfo>> viewMap;
    Map<String, Long> countMap;
    Map<String, ArrayList<PersonInfo>> map = new HashMap<>();

    void addContact() {
        PersonInfo personInfo = new PersonInfo();

        System.out.println("Enter FirstName : ");
        String firstName = sc.nextLine();

        System.out.println("Enter lastName : ");
        String lastName = sc.nextLine();

        if (duplicateCheck(firstName + lastName)) {
            System.out.println("GIVEN NAME IS ALREADY EXISTS");
            return;
        }

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
        System.out.println("------------------------------------------");
        System.out.println(personInfo);
        System.out.println("Contact Added Successfully");
        System.out.println("------------------------------------------");
    }

    void displayContact() {
        System.out.println("------------------------------------------");
        for (PersonInfo display : list) {
            System.out.println(display);
        }
        System.out.println("------------------------------------------");
    }

    void deleteContact() {
        System.out.println("Enter First Name to delete : ");
        String deleteName = sc.nextLine();
        System.out.println("------------------------------------------");
        for (PersonInfo personInfo : list) {
            if (personInfo.getFirstName().equals(deleteName)) {
                list.remove(personInfo);
                System.out.println("Record is deleted Successfully...!");
                System.out.println("------------------------------------------");
                break;
            }
        }
    }

    void editContact(String editName) {
        for (PersonInfo personInfo : list) {
            if (personInfo.getFirstName().equals(editName)) {

                System.out.println("Edit First Name : ");
                String firstName = sc.nextLine();

                System.out.println("Edit Last Name ; ");
                String lastName = sc.nextLine();

                if (duplicateCheck(firstName + lastName)) {
                    System.out.println("GIVEN NAME IS ALREADY EXISTS");
                    return;
                }
                System.out.println("Edit Address Name ; ");
                String address = sc.nextLine();

                System.out.println("Edit city Name ; ");
                String city = sc.nextLine();

                System.out.println("Edit state Name ; ");
                String state = sc.nextLine();

                System.out.println("Edit email Name ; ");
                String emailId = sc.nextLine();

                System.out.println("Edit Zip Name ; ");
                String zip = sc.nextLine();

                System.out.println("Edit phoneNumber Name ; ");
                String phoneNumber = sc.nextLine();

                personInfo.setFirstName(firstName);
                personInfo.setLastName(lastName);
                personInfo.setAddress(address);
                personInfo.setCity(city);
                personInfo.setState(state);
                personInfo.setEmailID(emailId);
                personInfo.setZip(zip);
                personInfo.setPhoneNumber(phoneNumber);
                System.out.println(personInfo);
                flag++;
            }
        }
    }

    void newAddressBook() {
        System.out.println("Enter Name Of Address Book : ");
        String addressBookName = sc.nextLine().toUpperCase();
        System.out.println("------------------------------------------");
        if (map.containsKey(addressBookName)) {
            System.out.println("AddressBook Already Exists");
            return;
        }
        list = new ArrayList<>();
        map.put(addressBookName, list);
        System.out.println(addressBookName);
        operationInBook();

        System.out.println("------------------------------------------");
    }

    public void openAddressBook() {
        System.out.println("AddressBooks : " + map.keySet());
        if (map.isEmpty()) {
            System.out.println("There Is No AddressBook Available");
        } else {
            System.out.println("Enter The Name Of Old AddressBook You Want To Open : ");
            String addBookDetails = sc.nextLine().toUpperCase();
            if (map.containsKey(addBookDetails)) {
                list = map.get(addBookDetails);
                operationInBook();
            } else {
                System.out.println("AddressBook Doesn't Exists!");
            }
        }
    }

    private void operationInBook() {
        boolean exit = true;
        while (exit) {
            System.out.print(" 1.ADD PERSON DETAILS ");
            System.out.print(" 2.EDIT PERSON DETAILS");
            System.out.print(" 3.DELETE PERSON DETAILS ");
            System.out.print(" 4.DISPLAY PERSON DETAILS ");
            System.out.println(" 0.EXIT ");
            System.out.print("->");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    editAddressBook();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    displayContact();
                    break;
                case 0:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }
            writeFileIO();
            try {
                writeCSV();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void displayAddressBook() {
        System.out.println("------------------------------------------");
        for (Map.Entry<String, ArrayList<PersonInfo>> displayBook : map.entrySet()) {
            System.out.println(displayBook.getKey());  // name
            //String key = displayBook.getKey();
            System.out.println(displayBook.getValue()); // data
            //ArrayList<PersonInfo> value = displayBook.getValue();
        }
        System.out.println("------------------------------------------");
    }

    void editAddressBook() {
        flag = 0;
        System.out.println("Enter First Name to update : ");
        String editName = sc.nextLine();
        for (Map.Entry<String, ArrayList<PersonInfo>> entry : map.entrySet()) {
            list = entry.getValue();
            editContact(editName);
        }
        if (flag == 0) {
            System.out.println("Record Not Found");
        }
    }

    public boolean duplicateCheck(String name) {
        PersonInfo check = list.stream().filter(i -> name.equals(i.getFirstName() + i.getLastName())).findFirst().orElse(null);
        return check != null;
    }

    public void searchCity() {
        System.out.println("Enter City Name To Search Function : ");
        String citySearch = sc.nextLine();
        map.keySet().forEach(key -> map.get(key).stream()
                .filter(i -> i.city.equals(citySearch))
                .collect(Collectors.toList()).stream()
                .forEach(i -> System.out.println(i.toString())));
        flag++;
        System.out.println("NumberOfPersonsInCity->" + "[" + flag + "]");
    }

    public void searchState() {
        System.out.println("Enter State Name To Search Function : ");
        String stateSearch = sc.nextLine();
        map.keySet().forEach(key -> map.get(key).stream()
                .filter(i -> i.state.equals(stateSearch))
                .collect(Collectors.toList())
                .forEach(i -> System.out.println(i.toString())));
        flag++;
        System.out.println("NumberOfPersonsInState->" + "[" + flag + "]");

    }

    public void viewByCity() {
        maps = new HashMap<>();
        for (String y : map.keySet()) {
            for (Map.Entry<String, List<PersonInfo>> entry : map.get(y).stream()
                    .collect(groupingBy(PersonInfo::getCity)).entrySet()) {
                String key = entry.getKey();
                List<PersonInfo> value = entry.getValue();
                maps.merge(key, value, (city, person) -> Stream.concat(city.stream(), person.stream()).toList());
            }
        }
        maps.forEach(((key, value) -> System.out.println("NumberOfPersonInCity" + "-->>" + value.size() + "{" + key.toUpperCase() + "}" + "->" + value + "<-")));
    }

    public void viewByState() {
        maps = new HashMap<>();
        for (String y : map.keySet()) {
            for (Map.Entry<String, List<PersonInfo>> entry : map.get(y).stream()
                    .collect(groupingBy(PersonInfo::getState)).entrySet()) {
                String key = entry.getKey();
                List<PersonInfo> value = entry.getValue();
                maps.merge(key, value, (state, person) -> Stream.concat(state.stream(), person.stream()).toList());
            }
        }
        maps.forEach(((key, value) -> System.out.println("NumberOfPersonInState" + "-->>" + value.size() + "{" + key.toUpperCase() + "}" + "->" + value + "<-")));
    }

    public void countByCity() {
        countMap = new HashMap<>();
        map.keySet().stream().flatMap(y -> map.get(y).stream()
                .collect(groupingBy(PersonInfo::getCity, counting())).entrySet().stream()).forEach(entry -> {
            String key = entry.getKey();
            Long value = entry.getValue();
            countMap.merge(key, value, Long::sum);
        });
        countMap.forEach(((key, value) -> System.out.println("{" + key.toUpperCase() + "}" + "->" + value + "<-")));
    }

    public void sortByPersonName() {
        list.stream()
                .sorted(Comparator.comparing((PersonInfo contact) -> contact.getFirstName()))
                .forEach(System.out::println);
    }

    public void sortByPersonNameInBooks() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream().collect(groupingBy(PersonInfo::getFirstName))
                .forEach((key, value) -> viewMap.merge(key.toUpperCase(), value, (firstName, details) -> {
                    firstName.addAll(details);
                    return firstName;
                })));
        viewMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

    }

}
  
