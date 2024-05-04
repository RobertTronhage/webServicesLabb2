package se.tronhage.webserviceslabb2.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import se.tronhage.webserviceslabb2.communication.PersonServiceClient;
import se.tronhage.webserviceslabb2.model.Person;

import java.util.List;

@Component
public class ConsoleMenu {

    @Autowired
    IO io;

    @Autowired
    PersonServiceClient personServiceClient;

    public void mainMenu() {
        int choice = 0;

        do {
            io.addString("""
                    choose option:
                    0 - exit application
                    1 - add new person
                    2 - display persons
                    3 - edit person
                    """);

            choice = io.getValidIntegerInput(0, 3);

            switch (choice) {
                case 0 -> System.exit(0);
                case 1 -> addPerson();
                case 2 -> displayPersonMenu();
                case 3 -> editPerson();
            }

        } while (true);
    }

    public void addPerson() {
        int choice = 0;
        do {
            io.addString("Enter '0' to abort");
            io.addString("Enter Person id and press enter:\n");
            Long id = io.getValidLongInput(0,Long.MAX_VALUE);

            if (id == 0){
                break;
            }

            io.addString("Enter full name:\n");
            String name = io.getString();

            io.addString("enter age:\n");
            int age = io.getValidIntegerInput(0,Integer.MAX_VALUE);

            io.addString("Enter city:\n");
            String city = io.getString();

            Person p = new Person(id,name,age,city);

            personServiceClient.addNewPerson(p);

            io.addString("Person added successfully");

            io.addString("Do you want to add another person? Enter '1' for Yes, '0' to abort:");
            choice = io.getValidIntegerInput(0, 1);

        } while (choice != 0);
    }

    public void displayPersonMenu() {
        int choice = 0;
        do {
            io.addString("""
                    choose option:
                    0 - Back to main menu
                    1 - Display all persons in database
                    2 - Search by person id
                    3 - Search by name
                    4 - Search by age
                    5 - Search by city
                    """);

            choice = io.getValidIntegerInput(0, 5);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    List<Person> allPersons = personServiceClient.allPersons();
                    for (Person p : allPersons) {
                        io.addString(p.toString());
                    }
                }
                case 2 -> {
                    io.addString("Enter search:\n");

                    Long searchId = io.getValidLongInput(0, Long.MAX_VALUE);

                    List<Person> foundPersons = personServiceClient.searchId(searchId);

                    for (Person p : foundPersons) {
                        io.addString(p.toString());
                    }
                }
                case 3 -> {
                    io.addString("Enter search:\n");
                    String searchName = io.getString();

                    List<Person> foundPersons = personServiceClient.searchName(searchName);

                    for (Person p : foundPersons) {
                        io.addString(p.toString());
                    }
                }
                case 4 -> {
                    io.addString("Enter search:\n");
                    int searchAge = io.getValidIntegerInput(0, Integer.MAX_VALUE);

                    try {
                        List<Person> foundPersons = personServiceClient.searchAge(searchAge);

                        for (Person p : foundPersons) {
                            io.addString(p.toString());
                        }
                    } catch (WebClientResponseException.NotFound e) {
                        io.addString("no results found\n");
                    }
                }
                case 5 -> {
                    io.addString("Enter search:\n");
                    String searchCity = io.getString();

                    List<Person> foundPersons = personServiceClient.searchCity(searchCity);

                    for (Person p : foundPersons) {
                        io.addString(p.toString());
                    }
                }
            }

        } while (choice != 0);
    }

    public void editPerson() {
        int choice = 0;
        do {
            io.addString("""
                    choose option:
                    0 - exit application
                    1 - Change name
                    2 - Change age
                    3 - Change city
                    """);

            choice = io.getValidIntegerInput(0, 3);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> io.addString("lägga till pers");
                case 2 -> io.addString("visa pers");
                case 3 -> io.addString("redigera pers");
            }

        } while (choice != 0);
    }

    public void updateName(){

    }


}
