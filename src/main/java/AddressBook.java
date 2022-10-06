import clojure.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import org.checkerframework.checker.units.qual.A;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AddressBook {
    public int flag = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<PersonInfo> list;
    List<PersonInfo> file;
    Map<String, List<PersonInfo>> maps;
    Map<String, List<PersonInfo>> viewMap;
    Map<String, Long> countMap;

    Map<String, ArrayList<PersonInfo>> map = new HashMap<>();
    ArrayList<PersonInfo> contactsArrayList;

    void addContact() {
        PersonInfo personInfo = new PersonInfo();

        System.out.println("Enter FirstName : ");
        String firstName = sc.nextLine();

        if (duplicateCheck(firstName)) {
            System.out.println("GIVEN NAME IS ALREADY EXISTS");
            return;
        }

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

                if (duplicateCheck(firstName)) {
                    System.out.println("GIVEN NAME IS ALREADY EXISTS");
                    return;
                }

                System.out.println("Edit Last Name ; ");
                String lastName = sc.nextLine();

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
            writeAddressBookJson();
            writeCSV();
        }
    }

    void displayAddressBook() {
        System.out.println("------------------------------------------");
        for (Map.Entry<String, ArrayList<PersonInfo>> displayBook : map.entrySet()) {
            System.out.println(displayBook.getKey());  // name
            System.out.println(displayBook.getValue()); // data
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
        PersonInfo check = list.stream().filter(i -> name.equals(i.getFirstName())).findFirst().orElse(null);
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

    public void sortByCity() {
        list.stream()
                .sorted(Comparator.comparing((PersonInfo contact) -> contact.getCity()))
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

    public void writeFileIO() {
        StringBuilder buffer = new StringBuilder("");
        map.keySet().forEach(key -> map.get(key).forEach(c -> buffer.append(c.toString().concat("\n"))));
        try {
            Path path = Paths.get("./addressBook.txt");
            Files.write(path, buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadFileIO() {
        try {
            Path path = Paths.get("./addressBook.txt");
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data Added In File Successfully!!!");
    }

    public void writeCSV() {
        try {
            List<String[]> file = new ArrayList<>();
            CSVWriter csvWriter = new CSVWriter(new FileWriter("AddressBook.csv"));
            String[] header = {" firstName ", " lastName ", " address ", " city ", " state ", " emailID ", " zip ", " phoneNumber "};
            csvWriter.writeNext(header);
            map.values().forEach(value -> value.stream().map(contact -> new String[]{contact.firstName,
                    contact.lastName, contact.address, contact.city, contact.state, contact.zip,
                    contact.phoneNumber, contact.emailID}).forEach(info -> file.add(info)));
            csvWriter.writeAll(file);
            csvWriter.flush();
            System.out.println("Data entered");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readCSV() {
        String file = "AddressBook.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(" ,");

                for (String index : row) {
                    System.out.println(index);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeAddressBookJson() {
        try {
            file = new ArrayList<>();
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("addressBook.json");
            map.values().forEach(value -> file.addAll(value));
            String json = gson.toJson(file);
            writer.write(json);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void readAddressBookJson() {
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("addressBook.json"));
            PersonInfo[] file = gson.fromJson(reader, PersonInfo[].class);
            List<PersonInfo> person = Arrays.asList(file);
            person.forEach(System.out::println);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Data Read Successfully");
    }
    public Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookdb?useSSL=false";
        String userName = "root";
        String password = "yash@2028";
        Connection connection;
        System.out.println("Connecting To Database : " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection Is Successful : " + connection);
        return connection;
    }

    public void retrieveData() {
        List<PersonInfo> sqlList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from addressbook_db";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PersonInfo person = new PersonInfo();
                person.setFirstName(resultSet.getString("first_Name"));
                person.setLastName(resultSet.getString("Last_name"));
                person.setAddress(resultSet.getString("address"));
                person.setCity(resultSet.getString("city"));
                person.setState(resultSet.getString("state"));
                person.setZip(resultSet.getString("zip"));
                person.setPhoneNumber(resultSet.getString("phone_Number"));
                person.setEmailID(resultSet.getString("email"));

                sqlList.add(person);
            }
            sqlList.forEach(System.out::println);
            System.out.println("Data Retrieve Successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

